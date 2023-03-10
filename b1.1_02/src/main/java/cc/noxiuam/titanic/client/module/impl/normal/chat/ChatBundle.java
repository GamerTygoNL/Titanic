package cc.noxiuam.titanic.client.module.impl.normal.chat;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.impl.BooleanSetting;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatBundle extends AbstractModule {

    private final List<String> chatMessageHistory = new ArrayList<>();
    public int chatMessageIndex = 0;

    private final BooleanSetting chatHistory;
    private final BooleanSetting chatBackground;

    public ChatBundle() {
        super("chatBundle", "Chat", false);
        initSettings(
                chatHistory = new BooleanSetting("chatHistory", "Own Message History", false),
                chatBackground = new BooleanSetting("chatBackground", "Chat Background", true)
        );
    }

}
