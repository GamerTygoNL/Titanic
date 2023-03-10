package cc.noxiuam.titanic.event.impl.chat;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatSendEvent extends AbstractEvent {
    
    private String message;
    
}
