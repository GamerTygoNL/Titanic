package cc.noxiuam.titanic.event.impl.keyboard;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyboardEvent extends AbstractEvent {

    private int key;

}
