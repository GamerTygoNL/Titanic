package cc.noxiuam.titanic.client;

import cc.noxiuam.titanic.bridge.Bridge;
import cc.noxiuam.titanic.client.command.CommandManager;
import cc.noxiuam.titanic.client.module.ModuleManager;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.event.AbstractEvent;
import cc.noxiuam.titanic.event.EventManager;

import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiChat;
import net.minecraft.src.ScreenShotHelper;
import org.lwjgl.input.Keyboard;

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
        logger.info("Created Profile Manager");

        moduleManager = new ModuleManager();
        logger.info("Created Module Manager");

        new CommandManager();
        logger.info("Created Command Manager");

        eventManager.addEvent(KeyboardEvent.class, this::handleKeyboard);
    }

    private void handleKeyboard(KeyboardEvent event) {
        Minecraft mc = bridge.getMinecraftBridge().bridge$getMinecraft();

        if (event.getKey() == Keyboard.KEY_F2) {
            mc.ingameGUI.addChatMessage(ScreenShotHelper.saveScreenshot(Minecraft.getMinecraftDir(), mc.displayWidth, mc.displayHeight));
            mc.isTakingScreenshot = true;
        }

        if (event.getKey() == Keyboard.KEY_SLASH) {
            bridge.getMinecraftBridge()
                    .bridge$getMinecraft()
                    .displayGuiScreen(new GuiChat(true));
        }

    }

}
