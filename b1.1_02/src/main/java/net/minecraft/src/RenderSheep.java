package net.minecraft.src;

public class RenderSheep extends RenderLiving {

    public RenderSheep(ModelBase modelbase, ModelBase modelbase1, float f) {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }

    protected boolean func_176_a(EntitySheep entitysheep, int i) {
        loadTexture("/mob/sheep_fur.png");
        return i == 0 && !entitysheep.sheared;
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i) {
        return func_176_a((EntitySheep) entityliving, i);
    }
}
