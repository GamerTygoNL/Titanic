package cc.noxiuam.titanic.event.impl.world.player;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.EntityPlayer;

@Getter
@AllArgsConstructor
public class OnLivingUpdateEvent extends AbstractEvent {

    private final EntityPlayer player;

}
