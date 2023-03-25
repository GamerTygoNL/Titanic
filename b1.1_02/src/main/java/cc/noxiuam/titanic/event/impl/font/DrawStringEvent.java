package cc.noxiuam.titanic.event.impl.font;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DrawStringEvent extends AbstractEvent {

    private String string;

}
