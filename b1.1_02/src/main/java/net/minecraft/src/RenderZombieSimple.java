package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderZombieSimple extends RenderLiving {

    private final float field_204_f;

    public RenderZombieSimple(ModelBase modelbase, float f, float f1) {
        super(modelbase, f * f1);
        field_204_f = f1;
    }

    protected void func_175_a(EntityZombieSimple entityzombiesimple, float f) {
        GL11.glScalef(field_204_f, field_204_f, field_204_f);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f) {
        func_175_a((EntityZombieSimple) entityliving, f);
    }
}
