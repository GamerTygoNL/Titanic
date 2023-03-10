package net.minecraft.src;

public class RenderPig extends RenderLiving {

    public RenderPig(ModelBase modelbase, ModelBase modelbase1, float f) {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }

    protected boolean func_180_a(EntityPig entitypig, int i) {
        loadTexture("/mob/saddle.png");
        return i == 0 && entitypig.rideable;
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i) {
        return func_180_a((EntityPig) entityliving, i);
    }
}
