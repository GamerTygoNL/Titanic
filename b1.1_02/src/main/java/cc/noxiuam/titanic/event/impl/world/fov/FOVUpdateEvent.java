package cc.noxiuam.titanic.event.impl.world.fov;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FOVUpdateEvent extends AbstractEvent {

    private final float partialTicks;
    private float fov;

}
