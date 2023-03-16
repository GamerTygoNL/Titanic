package cc.noxiuam.titanic.event.impl.perspective;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ViewBobbingSetupEvent extends AbstractEvent {

    private float value;

}
