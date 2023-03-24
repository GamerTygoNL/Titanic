package cc.noxiuam.titanic.client.module.impl.normal;

import cc.noxiuam.titanic.Titanic;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.TickEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.network.PacketReceivedEvent;
import net.minecraft.src.Packet51MapChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoadingFix extends AbstractModule {

    public final List<Long> cooldown = new ArrayList<>();

    private final KeybindSetting refreshKeybind;
    private final BooleanSetting automaticRefresh;

    public ChunkLoadingFix() {
        super("chunkLoadingFix", "Chunk Loading Fix", false);
        this.initSettings(
                this.refreshKeybind = new KeybindSetting("refreshKeybind", "Refresh Keybind", 0),
                this.automaticRefresh = new BooleanSetting("automaticRefresh", "Automatically Refresh Blocks", true)
        );
        this.addEvent(PacketReceivedEvent.class, this::onPacket);
        this.addEvent(TickEvent.class, this::onTick);

        // manual refreshing regardless if it's enabled or not
        Titanic.getInstance().getEventManager().addEvent(KeyboardEvent.class, event -> {
            if (event.getKey() == this.refreshKeybind.value()) {
                this.mc.renderGlobal.loadRenderers();
            }
        });
    }

    private void onTick(TickEvent ignored) {
        this.cooldown.removeIf(n -> n < System.currentTimeMillis() - 5000L);
    }

    private void onPacket(PacketReceivedEvent event) {
        if (event.getPacket() instanceof Packet51MapChunk && this.automaticRefresh.value() && this.cooldown.size() == 0) {
            for (int i = 0; i < 15; i++) {
                this.cooldown.add(System.currentTimeMillis());
            }
            this.mc.renderGlobal.loadRenderers();
        }
    }

}
