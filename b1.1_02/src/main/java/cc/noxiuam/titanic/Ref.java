package cc.noxiuam.titanic;

import cc.noxiuam.titanic.client.config.ConfigManager;
import cc.noxiuam.titanic.client.module.ModuleManager;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.event.EventManager;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;

/**
 * Made because I am too lazy to type things out.
 */
@UtilityClass
public class Ref {

    public EventManager getEventManager() {
        return Titanic.getInstance().getEventManager();
    }

    public ModuleManager getModuleManager() {
        return Titanic.getInstance().getModuleManager();
    }

    public ConfigManager getConfigManager() {
        return Titanic.getInstance().getConfigManager();
    }

    public ProfileManager getProfileManager() {
        return Titanic.getInstance().getProfileManager();
    }

    public Titanic getTitanic() {
        return Titanic.getInstance();
    }

    public Minecraft getMinecraft() {
        return Titanic.getInstance()
                .getBridge()
                .getMinecraftBridge()
                .bridge$getMinecraft();
    }

}
