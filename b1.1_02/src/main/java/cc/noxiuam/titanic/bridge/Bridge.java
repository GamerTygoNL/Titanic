package cc.noxiuam.titanic.bridge;

import cc.noxiuam.titanic.bridge.type.MinecraftBridge;
import cc.noxiuam.titanic.client.util.Logger;
import lombok.Getter;

@Getter
public class Bridge {

    private final Logger logger = new Logger("Bridge");

    public static IBridge iBridgeInstance;

    private MinecraftBridge minecraftBridge;

    public Bridge() {
        this.setupIBridge(new BridgeImpl());
    }

    /**
     * Sets up a new Bridge Implementation.
     *
     * @param bridge The new bridge instance.
     */
    public void setupIBridge(IBridge bridge) {
        if (iBridgeInstance != null) {
            logger.error("IBridge can not be reinitialized due to it already being initialized.");
            return;
        }

        iBridgeInstance = bridge;
        iBridgeInstance.onEnable();
    }

    /**
     * Setup Minecraft Bridge instance for use
     * This only exists because Minecraft.getMinecraft() does not.
     *
     * @param bridge The Minecraft class, usually on older versions it will be "MinecraftImpl"
     */
    public void setupMinecraftBridge(MinecraftBridge bridge) {
        if (minecraftBridge != null) {
            logger.error("Minecraft Bridge can not be reinitialized due to it already being initialized.");
            return;
        }

        minecraftBridge = bridge;
        logger.info("Minecraft Bridge is \"" + bridge.getClass().getSimpleName() + "\"");
    }

}
