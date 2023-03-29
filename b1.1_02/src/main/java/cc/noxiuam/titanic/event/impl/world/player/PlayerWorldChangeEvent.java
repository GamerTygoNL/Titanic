package cc.noxiuam.titanic.event.impl.world.player;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.World;

@Getter
@AllArgsConstructor
public class PlayerWorldChangeEvent extends AbstractEvent {

    private final World prevWorld;
    private final World newWorld;

}
