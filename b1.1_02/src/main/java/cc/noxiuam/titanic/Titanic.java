package cc.noxiuam.titanic;

import cc.noxiuam.titanic.bridge.Bridge;
import cc.noxiuam.titanic.client.command.CommandManager;
import cc.noxiuam.titanic.client.config.ConfigManager;
import cc.noxiuam.titanic.client.module.ModuleManager;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.client.ui.impl.editor.HudLayoutEditor;
import cc.noxiuam.titanic.client.util.Logger;
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
    private final ConfigManager configManager;
    private final ProfileManager profileManager;
    private final ModuleManager moduleManager;

    public Titanic(Minecraft mc) {
        instance = this;
        Logger logger = new Logger("Initialization");

        eventManager = new EventManager();
        logger.info("Initialized Event Manager");

        configManager = new ConfigManager();
        logger.info("Initialized Config Manager");

        (bridge = new Bridge()).setupMinecraftBridge(mc);
        logger.info("Initialized Bridge");

        profileManager = new ProfileManager();
        logger.info("Initialized Profile Manager");

        moduleManager = new ModuleManager();
        logger.info("Initialized Module Manager");

        new CommandManager();
        logger.info("Initialized Command Manager");

        eventManager.addEvent(KeyboardEvent.class, this::handleKeyboard);
    }

    private void handleKeyboard(KeyboardEvent event) {
        int key = event.getKey();
        Minecraft mc = bridge.getMinecraftBridge().bridge$getMinecraft();

        if (key == Keyboard.KEY_RSHIFT) {
            mc.displayGuiScreen(new HudLayoutEditor());
            return;
        }

        if (key == Keyboard.KEY_F2) {
            mc.ingameGUI.addChatMessage(ScreenShotHelper.saveScreenshot(Minecraft.getMinecraftDir(), mc.displayWidth, mc.displayHeight));
            mc.isTakingScreenshot = true;
            return;
        }

        if (key == Keyboard.KEY_SLASH) {
            bridge.getMinecraftBridge()
                    .bridge$getMinecraft()
                    .displayGuiScreen(new GuiChat(true));
        }

    }

}
