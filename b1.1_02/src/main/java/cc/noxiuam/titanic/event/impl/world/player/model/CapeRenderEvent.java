package cc.noxiuam.titanic.event.impl.world.player.model;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.EntityPlayer;

@AllArgsConstructor
public class CapeRenderEvent extends AbstractEvent {

    @Getter private EntityPlayer player;
    public float value;

}
