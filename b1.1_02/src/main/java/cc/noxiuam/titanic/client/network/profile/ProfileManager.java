package cc.noxiuam.titanic.client.network.profile;

import cc.noxiuam.titanic.client.Titanic;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.event.impl.player.PlayerLoadEvent;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.src.EntityPlayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileManager {

    // A list of all the player profiles that are downloaded.
    private final List<Profile> playerProfiles = new ArrayList<>();

    private final Logger logger = new Logger("Profile Management");
    private final JsonParser parser = new JsonParser();

    public ProfileManager() {
        Titanic.getInstance().getEventManager().addEvent(PlayerLoadEvent.class, this::onPlayerLoad);
    }

    /**
     * Fixes player skins, and provides RetroMC cape support.
     */
    private void onPlayerLoad(PlayerLoadEvent event) {
        EntityPlayer player = event.getPlayer();

        String username = player.field_771_i;

        if (username.equalsIgnoreCase("Noxiuam")) {
            username = "GitCLI";
        }

        //player.field_20047_bv = "https://mc-heads.net/skin/" + username;
        //player.playerCloakUrl = SkinUtil.getCapeURL(username);

        String retroCape = "http://assets.retromc.org/capes/" + username + ".png";

        if (this.isValidURL(retroCape)) {
            player.field_20067_q = retroCape;
        }

        System.out.println("exists=" + this.profileExists(username));

        if (this.profileExists(username)) {
            player.playerProfile = this.getProfile(username);
            System.out.println("hasCape" + player.playerProfile.isHasCape());
            if (player.playerProfile.isHasCape()) {
                player.field_20067_q = "https://noxiuam.cc/titanic-client/cosmetic/cape/AP_" + player.playerProfile.getRank() + ".png";
            }
        }

    }

    /**
     * Pulls the remote profile from the website, and creates it client-sided.
     * If the profile does not exist, a default profile gets returned.
     *
     * @param username The username to pull a profile from.
     */
    public void downloadAndAddProfile(String username) {
        logger.info("Downloading " + username + (username.endsWith("s") ? "'" : "'s") + " profile");

        try {
            URL url = new URL("https://noxiuam.cc/titanic-client/player/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JsonObject playerObj = parser.parse(br).getAsJsonObject();

            boolean hasCape = playerObj.get("hasCape").getAsBoolean();
            String rank = playerObj.get("rank").getAsString().toUpperCase();

            Profile profile = new Profile(username, hasCape, rank);
            System.out.println(profile.toString());

            this.playerProfiles.add(profile);
            connection.disconnect();
        } catch (Exception e) {
            // The profile did not exist.
        }

    }

    @SneakyThrows
    public void downloadAllProfiles() {
        List<String> names = new ArrayList<>();

        URL url = new URL("https://noxiuam.cc/titanic-client/profiles.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        JsonArray serverNames = new JsonParser().parse(br).getAsJsonArray();

        for (int i = 0; i < serverNames.size(); i++) {
            names.add(serverNames.get(i).toString().replaceAll("\"", ""));
        }

        for (String name : names) {
            this.downloadAndAddProfile(name);
        }
    }

    /**
     * Gets a player profile from playerProfiles.
     *
     * @param target The username of the target player.
     */
    public Profile getProfile(String target) {

        for (Profile profile : this.playerProfiles) {
            if (profile.getUsername().equals(target)) {
                return profile;
            }
        }

        // Fake profile to prevent crashes.
        return new Profile(target, false, "DEFAULT");
    }

    /**
     * Checks if a profile exists.
     *
     * @param username The target player.
     */
    public boolean profileExists(String username) {
        for (Profile profile : this.playerProfiles) {
            if (profile.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a URL is valid or not.
     *
     * @param url The url to check
     */
    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
