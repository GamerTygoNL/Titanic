package cc.noxiuam.titanic.event.impl.gui;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.ScaledResolution;

@Getter
@AllArgsConstructor
public class GuiDrawEvent extends AbstractEvent {

    private ScaledResolution resolution;

}
