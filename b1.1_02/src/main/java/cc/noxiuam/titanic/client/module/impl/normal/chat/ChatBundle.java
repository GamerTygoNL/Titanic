package cc.noxiuam.titanic.client.module.impl.normal.chat;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.event.impl.gui.chat.ChatBackgroundDrawEvent;
import cc.noxiuam.titanic.event.impl.gui.chat.PreChatMessageUpdateEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatBundle extends AbstractModule {

    private final List<String> chatMessageHistory = new ArrayList<>();
    public int chatMessageIndex = -1;

    private final BooleanSetting chatHistory;
    private final BooleanSetting chatBackground;

    private final String empty = "";

    public ChatBundle() {
        super("chatBundle", "Chat Editor", false);
        initSettings(
                chatHistory = new BooleanSetting("chatHistory", "Message History", false),
                chatBackground = new BooleanSetting("chatBackground", "Chat Background", true)
        );

        this.addEvent(PreChatMessageUpdateEvent.class, this::onPreMessageUpdate);
        this.addEvent(ChatBackgroundDrawEvent.class, this::onChatBackgroundDraw);
    }

    private void onPreMessageUpdate(PreChatMessageUpdateEvent event) {
        if (chatHistory.value()) {
            int key = event.getKey();

            event.cancel();

            if (key == 200) {
               this.updateCurrentMessageFromHistory(event, -1);
            } else if (key == 208) {
                this.updateCurrentMessageFromHistory(event, 1);
            }
        }
    }

    private void updateCurrentMessageFromHistory(PreChatMessageUpdateEvent event, int increaseAmount) {
        int newIndex = this.chatMessageIndex + increaseAmount;
        int historySize = chatMessageHistory.size();

        if (newIndex < 0) {
            newIndex = 0;
        }

        if (newIndex > historySize) {
            newIndex = historySize;
        }

        if (newIndex != this.chatMessageIndex) {
            if (newIndex == historySize) {
                this.chatMessageIndex = historySize;
                event.setMessage(this.empty);
            } else {
                if (this.chatMessageIndex == historySize) {
                    event.setMessage(this.empty);
                }
                event.setMessage(chatMessageHistory.get(newIndex));
                this.chatMessageIndex = newIndex;
            }
        }
    }

    private void onChatBackgroundDraw(ChatBackgroundDrawEvent event) {
        if (!chatBackground.value()) {
            event.cancel();
        }
    }

}
