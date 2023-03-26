package cc.noxiuam.titanic.event.impl.mouse;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerLookInputEvent extends AbstractEvent {

    private float dx;
    private float dy;
    private final float partialTicks;

}
