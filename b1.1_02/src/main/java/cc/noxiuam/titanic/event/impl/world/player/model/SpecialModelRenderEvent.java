package cc.noxiuam.titanic.event.impl.world.player.model;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;

@Getter
@AllArgsConstructor
public class SpecialModelRenderEvent extends AbstractEvent {

    private EntityLiving entityLiving;
    private ModelBase mainModel;
    private ModelBase renderPassModel;

}
