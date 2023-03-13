package cc.noxiuam.titanic.event.impl.player.model;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.ModelBiped;

@Getter
@AllArgsConstructor
public class PlayerModelRenderEvent extends AbstractEvent {

    private ModelBiped model;

}
