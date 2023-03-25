package cc.noxiuam.titanic.client.module.impl.normal.performance;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.event.impl.world.block.BlockParticleRenderEvent;
import cc.noxiuam.titanic.event.impl.world.block.BlockRenderEvent;
import net.minecraft.src.BlockCrops;
import net.minecraft.src.BlockRedstoneOre;
import net.minecraft.src.BlockTorch;

public class PerformanceModule extends AbstractModule {

    private final BooleanSetting showCrops, showTorchParticles;

    public PerformanceModule() {
        super("performanceHelper", "Performance", false);

        this.initSettings(
                this.showCrops = new BooleanSetting("showCrops", "Show Crops", true),
                this.showTorchParticles = new BooleanSetting("showTorchParticles", "Show Torch Particles", true)
        );

        this.showCrops.onUpdate(value -> {
            if (Ref.getMinecraft().renderGlobal != null) {
                Ref.getMinecraft().renderGlobal.loadRenderers();
            }
        });

        this.addEvent(BlockRenderEvent.class, this::onBlockRender);
        this.addEvent(BlockParticleRenderEvent.class, this::onBlockParticleRender);
    }

    private void onBlockRender(BlockRenderEvent event) {
        if (!this.showCrops.value() &&
                (event.getBlock() instanceof BlockCrops)
        ) {
            event.cancel();
        }
    }

    private void onBlockParticleRender(BlockParticleRenderEvent event) {
        if (!this.showTorchParticles.value() &&
                (event.getBlock() instanceof BlockTorch || event.getBlock() instanceof BlockRedstoneOre)
        ) {
            event.cancel();
        }
    }

}
