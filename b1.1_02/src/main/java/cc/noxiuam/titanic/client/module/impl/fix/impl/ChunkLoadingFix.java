package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.event.impl.TickEvent;
import cc.noxiuam.titanic.event.impl.network.PacketReceivedEvent;
import net.minecraft.src.Packet51MapChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoadingFix extends AbstractFixModule {

    public final List<Long> cooldown = new ArrayList<>();

    public ChunkLoadingFix() {
        super("Chunk Loading Fix");
        this.addEvent(PacketReceivedEvent.class, this::onPacket);
        this.addEvent(TickEvent.class, this::onTick);
    }

    private void onTick(TickEvent ignored) {
        this.cooldown.removeIf(n -> n < System.currentTimeMillis() - 5000L);
    }

    private void onPacket(PacketReceivedEvent event) {
        if (event.getPacket() instanceof Packet51MapChunk && this.cooldown.size() == 0) {
            for (int i = 0; i < 15; i++) {
                this.cooldown.add(System.currentTimeMillis());
            }
            mc.renderGlobal.loadRenderers();
        }
    }

}
