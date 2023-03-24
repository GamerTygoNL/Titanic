package cc.noxiuam.titanic.client.module.impl.normal.chat;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.event.impl.chat.ChatReceivedEvent;
import cc.noxiuam.titanic.event.impl.chat.ChatSendEvent;

public class AutoLogin extends AbstractModule {

    private final StringSetting password;
    private final BooleanSetting autoUpdatePassword;
    private final BooleanSetting showUpdateMessages;

    public AutoLogin() {
        super("autoLogin", "Auto Login", false);
        this.initSettings(
                this.password = new StringSetting("autoLoginPassword", "Auto Login Password", "password"),
                this.autoUpdatePassword = new BooleanSetting("autoUpdatePassword", "Update Password on Login", true),
                this.showUpdateMessages = new BooleanSetting("showUpdateMessages", "Show Update Messages", true)
        );

        this.addEvent(ChatReceivedEvent.class, event -> {
            if (event.getMessage().equals(ChatColor.COLOR_CHAR + "cPlease log in using /login <password>.")) {
                this.mc.thePlayer.sendChatMessage("/login " + this.password.value());
            }
        });

        this.addEvent(ChatSendEvent.class, event -> {
            if (this.autoUpdatePassword.value() && event.getMessage().startsWith("/login")) {
                this.password.value(event.getMessage().replace("/login ", ""));
                if (this.showUpdateMessages.value()) {
                    this.mc.ingameGUI.addChatMessage("[Titanic] Updated password.");
                }
            }
        });
    }

}
