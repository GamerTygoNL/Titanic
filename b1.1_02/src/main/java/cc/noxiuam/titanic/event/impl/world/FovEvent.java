package cc.noxiuam.titanic.event.impl.world;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FovEvent extends AbstractEvent {

    private final float partialTicks;
    private float fov;

}
