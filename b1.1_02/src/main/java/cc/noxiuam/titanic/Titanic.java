package cc.noxiuam.titanic;

import cc.noxiuam.titanic.bridge.Bridge;
import cc.noxiuam.titanic.client.command.CommandManager;
import cc.noxiuam.titanic.client.config.ConfigManager;
import cc.noxiuam.titanic.client.module.ModuleManager;
import cc.noxiuam.titanic.client.network.profile.ProfileManager;
import cc.noxiuam.titanic.client.ui.module.editor.HudLayoutEditor;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.client.util.SmoothUtil;
import cc.noxiuam.titanic.event.EventManager;

import cc.noxiuam.titanic.event.impl.chat.ChatReceivedEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiChat;
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

        this.eventManager = new EventManager();
        logger.info("Initialized Event Manager");

        this.configManager = new ConfigManager();
        logger.info("Initialized Config Manager");

        (this.bridge = new Bridge()).setupMinecraftBridge(mc);
        logger.info("Initialized Bridge");

        this.profileManager = new ProfileManager();
        logger.info("Initialized Profile Manager");

        this.moduleManager = new ModuleManager();
        logger.info("Initialized Module Manager");

        this.configManager.readConfigs();
        logger.info("Reading Module Configuration Files");

        new CommandManager();
        logger.info("Initialized Command Manager");

        eventManager.addEvent(KeyboardEvent.class, this::handleKeyboard);
        eventManager.addEvent(ChatReceivedEvent.class, chatReceivedEvent -> new Logger("Chat").info(chatReceivedEvent.getMessage()));
        SmoothUtil.inject();

        Thread shutdownThread = new Thread(this.configManager::saveConfigs);
        Runtime.getRuntime().addShutdownHook(shutdownThread);
    }

    private void handleKeyboard(KeyboardEvent event) {
        int key = event.getKey();
        Minecraft mc = Ref.getMinecraft();

        if (key == Keyboard.KEY_RSHIFT) {
            mc.displayGuiScreen(new HudLayoutEditor());
            return;
        }

        if (key == Keyboard.KEY_SLASH) {
            this.bridge.getMinecraftBridge()
                    .bridge$getMinecraft()
                    .displayGuiScreen(new GuiChat(true));
        }

    }

}
