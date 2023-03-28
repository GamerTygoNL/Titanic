package cc.noxiuam.titanic.event.impl.network;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.Packet4UpdateTime;

@Getter
@AllArgsConstructor
public class PacketTimeUpdateEvent extends AbstractEvent {

    private final Packet4UpdateTime packet;

}
