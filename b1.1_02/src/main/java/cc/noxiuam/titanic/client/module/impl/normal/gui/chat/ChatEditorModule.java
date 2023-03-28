package cc.noxiuam.titanic.client.module.impl.normal.gui.chat;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.event.impl.gui.HotbarRenderEvent;
import cc.noxiuam.titanic.event.impl.gui.chat.ChatBackgroundDrawEvent;
import cc.noxiuam.titanic.event.impl.gui.chat.PreChatMessageUpdateEvent;
import lombok.Getter;
import net.minecraft.src.GuiChat;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatEditorModule extends AbstractModule {

    private final List<String> chatMessageHistory = new ArrayList<>();
    public int chatMessageIndex = -1;
    private final String empty = "";
    
    private final BooleanSetting chatHistory, chatBackground, showHotbarWhileTyping;

    public ChatEditorModule() {
        super("chatEditor", "Chat Editor", false);

        initSettings(
                this.chatHistory = new BooleanSetting("chatHistory", "Message History", false),
                this.chatBackground = new BooleanSetting("chatBackground", "Chat Background", true),
                this.showHotbarWhileTyping = new BooleanSetting("showHotbarWhileTyping", "Show Hotbar While Typing", true)
        );

        this.addEvent(HotbarRenderEvent.class, this::onHotbarRender);
        this.addEvent(PreChatMessageUpdateEvent.class, this::onPreMessageUpdate);
        this.addEvent(ChatBackgroundDrawEvent.class, this::onChatBackgroundDraw);
    }

    private void onHotbarRender(HotbarRenderEvent event) {
        if (this.showHotbarWhileTyping.value() && Ref.getMinecraft().currentScreen instanceof GuiChat) {
            event.cancel();
        }
    }

    private void onPreMessageUpdate(PreChatMessageUpdateEvent event) {
        if (this.chatHistory.value()) {
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
        int historySize = this.chatMessageHistory.size();

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
