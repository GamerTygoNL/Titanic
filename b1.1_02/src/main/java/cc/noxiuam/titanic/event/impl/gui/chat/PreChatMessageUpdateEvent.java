package cc.noxiuam.titanic.event.impl.gui.chat;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PreChatMessageUpdateEvent extends AbstractEvent {

    private int key;
    @Setter private String message;

}

