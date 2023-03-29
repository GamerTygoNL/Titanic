package cc.noxiuam.titanic.event.impl.gui;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortalOverlayDrawEvent extends AbstractEvent {

    public float timeInPortal;
    public float prevTimeInPortal;

    private final float partialTicks;

}

