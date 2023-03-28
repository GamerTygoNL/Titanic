package cc.noxiuam.titanic.client.module.impl.normal.gui.chat;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.event.impl.chat.ChatReceivedEvent;
import cc.noxiuam.titanic.event.impl.chat.ChatSendEvent;

public class AutoLoginModule extends AbstractModule {

    private final StringSetting password;
    private final BooleanSetting autoUpdatePassword;
    private final BooleanSetting showUpdateMessages;

    public AutoLoginModule() {
        super("autoLogin", "Auto Login", false);
        this.initSettings(
                this.password = new StringSetting("autoLoginPassword", "Auto Login Password", "password", 15),
                this.autoUpdatePassword = new BooleanSetting("autoUpdatePassword", "Update Password on Login", true),
                this.showUpdateMessages = new BooleanSetting("showUpdateMessages", "Show Update Messages", true)
        );

        this.addEvent(ChatReceivedEvent.class, event -> {
            if (event.getMessage().equals(Ref.ALPHA_PLACE_AUTH_MESSAGE)) {
                this.mc.thePlayer.sendChatMessage("/login " + this.password.value());
            }
        });

        this.addEvent(ChatSendEvent.class, event -> {
            if (this.autoUpdatePassword.value() && event.getMessage().startsWith("/login")) {
                this.password.value(event.getMessage().replace("/login ", ""));
                if (this.showUpdateMessages.value()) {
                    this.mc.ingameGUI.addChatMessage(ChatColor.DARK_AQUA + "[Titanic] " + ChatColor.WHITE + "Updated password.");
                }
            }
        });
    }

}
