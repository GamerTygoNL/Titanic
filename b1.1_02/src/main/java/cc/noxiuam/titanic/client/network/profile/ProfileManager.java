package cc.noxiuam.titanic.client.network.profile;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.network.cosmetic.Cosmetic;
import cc.noxiuam.titanic.client.util.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileManager {

    private final List<Profile> profileCache = new ArrayList<>();

    private final Logger logger = new Logger("Profile Management");

    public ProfileManager() {
        new Thread(new ProfileCacheRefreshThread()).start();
    }

    @SneakyThrows
    public void downloadAllProfiles() {
        List<String> names = new ArrayList<>();

        URL url = new URL("https://noxiuam.cc/titanic-client/profiles.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        JsonArray remoteNames = new JsonParser().parse(br).getAsJsonArray();

        for (int i = 0; i < remoteNames.size(); i++) {
            names.add(remoteNames.get(i).toString().replaceAll("\"", ""));
        }

        for (String name : names) {
            this.downloadAndAddProfile(name);
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
            JsonObject playerObj = new JsonParser().parse(br).getAsJsonObject();

            JsonObject cosmetic = playerObj.get("cosmetic").getAsJsonObject();
            String cosmeticName = cosmetic.get("name").getAsString();
            String cosmeticDescription = cosmetic.get("description").getAsString();
            boolean isCosmeticEquipped = cosmetic.get("equipped").getAsBoolean();
            String cosmeticType = cosmetic.get("type").getAsString();
            String cosmeticLocation = cosmetic.get("location").getAsString();

            Cosmetic equippedCosmetic = new Cosmetic(cosmeticName, cosmeticDescription, isCosmeticEquipped, cosmeticType, cosmeticLocation);
            Profile profile = new Profile(username, equippedCosmetic);
            this.profileCache.add(profile);

            connection.disconnect();
        } catch (Exception ignored) { }
    }

    /**
     * Checks if a profile exists.
     *
     * @param username The target player.
     */
    public boolean profileExists(String username) {
        for (Profile profile : this.profileCache) {
            if (profile.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets a player profile from playerProfiles.
     *
     * @param target The username of the target player.
     */
    public Profile getProfile(String target) {
        for (Profile profile : this.profileCache) {
            if (profile.getUsername().equals(target)) {
                return profile;
            }
        }

        return new Profile(target, null);
    }

    static class ProfileCacheRefreshThread implements Runnable {

        @Override
        public void run() {
            try {
                Ref.getTitanic().getProfileManager().downloadAllProfiles();
                Thread.sleep(60000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
