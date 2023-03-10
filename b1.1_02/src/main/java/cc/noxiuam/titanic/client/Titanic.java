package cc.noxiuam.titanic.client;

import cc.noxiuam.titanic.bridge.Bridge;
import cc.noxiuam.titanic.client.module.ModuleManager;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.event.EventManager;

import lombok.Getter;
import net.minecraft.client.Minecraft;

@Getter
public class Titanic {

    @Getter private static Titanic instance;
    
    public final boolean debug = false;

    private final Bridge bridge;
    private final EventManager eventManager;
    private final ProfileManager profileManager;
    private final ModuleManager moduleManager;

    public Titanic(Minecraft mc) {
        instance = this;

        Logger logger = new Logger("Initialization");
        logger.info("Created Logger");

        eventManager = new EventManager();
        logger.info("Created Event Manager");

        (bridge = new Bridge()).setupMinecraftBridge(mc);
        logger.info("Created Bridge");

        profileManager = new ProfileManager();
        profileManager.downloadAllProfiles();
        logger.info("Created Profile Manager");

        moduleManager = new ModuleManager();
        logger.info("Created Module Manager");

    }

}
