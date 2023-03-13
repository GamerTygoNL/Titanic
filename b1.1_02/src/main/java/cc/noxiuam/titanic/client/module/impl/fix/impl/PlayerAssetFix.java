package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.Titanic;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.client.util.http.HttpUtil;
import cc.noxiuam.titanic.event.impl.player.PlayerLoadEvent;
import net.minecraft.src.EntityPlayer;

/**
 * Fixes the following: Skins, Capes
 */
public class PlayerAssetFix extends AbstractFixModule {

    private final ProfileManager profileManager = Titanic.getInstance().getProfileManager();

    public PlayerAssetFix() {
        super("Player Asset Fix");
        addEvent(PlayerLoadEvent.class, this::fixPlayerAssets);
    }

    /**
     * Fixes player skins & applies custom capes, also has RetroMC cape support.
     */
    private void fixPlayerAssets(PlayerLoadEvent event) {
        EntityPlayer player = event.getPlayer();

        String username = player.field_771_i;

        if (username.equalsIgnoreCase("Noxiuam")) {
            username = "GitCLI";
        }

        player.field_20047_bv = "https://mc-heads.net/skin/" + username;
        //player.playerCloakUrl = SkinUtil.getCapeURL(username);

        String retroCape = "http://assets.retromc.org/capes/" + username + ".png";

        if (HttpUtil.isValidURL(retroCape)) {
            player.field_20067_q = retroCape;
        }

        if (profileManager.profileExists(username)) {
            player.playerProfile = profileManager.getProfile(username);
            if (player.playerProfile.isHasCape()) {
                player.field_20067_q = "https://noxiuam.cc/titanic-client/cosmetic/cape/AP_" + player.playerProfile.getRank() + ".png";
            }
        }
    }

}
