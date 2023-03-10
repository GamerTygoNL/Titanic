package cc.noxiuam.titanic.event.impl.world;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FovChangeEvent extends AbstractEvent {

    public float f2;
    public double d3;

}
