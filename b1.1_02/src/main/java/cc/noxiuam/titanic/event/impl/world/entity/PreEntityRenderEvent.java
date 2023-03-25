package cc.noxiuam.titanic.event.impl.world.entity;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.Entity;

@Getter
@AllArgsConstructor
public class PreEntityRenderEvent extends AbstractEvent {

    private Entity entity;

}
