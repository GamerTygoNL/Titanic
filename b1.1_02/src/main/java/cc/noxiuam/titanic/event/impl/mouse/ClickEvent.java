package cc.noxiuam.titanic.event.impl.mouse;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClickEvent extends AbstractEvent {
    private int mouseButton;
}
