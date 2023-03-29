package cc.noxiuam.titanic.event.impl.world.player;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;

@Getter
@AllArgsConstructor
public class PlayerBlockCollideEvent extends AbstractEvent {

    private final Entity entity;
    private final Block block;

}
