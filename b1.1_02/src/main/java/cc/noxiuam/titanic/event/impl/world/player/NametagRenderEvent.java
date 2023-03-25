package cc.noxiuam.titanic.event.impl.world.player;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.EntityPlayer;

@Getter
@AllArgsConstructor
public class NametagRenderEvent extends AbstractEvent {

    private EntityPlayer player;
    private float f3;

}
