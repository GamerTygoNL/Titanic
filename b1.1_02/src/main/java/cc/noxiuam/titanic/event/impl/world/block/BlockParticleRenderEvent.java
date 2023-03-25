package cc.noxiuam.titanic.event.impl.world.block;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.Block;

@Getter
@AllArgsConstructor
public class BlockParticleRenderEvent extends AbstractEvent {

    private Block block;

}
