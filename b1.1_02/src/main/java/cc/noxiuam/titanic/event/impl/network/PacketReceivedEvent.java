package cc.noxiuam.titanic.event.impl.network;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.Packet;

@Getter
@AllArgsConstructor
public class PacketReceivedEvent extends AbstractEvent {

    private Packet packet;

}
