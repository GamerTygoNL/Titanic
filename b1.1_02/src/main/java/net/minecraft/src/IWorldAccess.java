package net.minecraft.src;

public interface IWorldAccess {

    public abstract void func_934_a(int i, int j, int k);

    public abstract void markBlockRangeNeedsUpdate(int i, int j, int k, int l, int i1, int j1);

    public abstract void playSound(String s, double d, double d1, double d2,
                                   float f, float f1);

    public abstract void spawnParticle(String s, double d, double d1, double d2,
                                       double d3, double d4, double d5);

    public abstract void obtainEntitySkin(Entity entity);

    public abstract void releaseEntitySkin(Entity entity);

    public abstract void updateAllRenderers();

    public abstract void playRecord(String s, int i, int j, int k);

    public abstract void func_935_a(int i, int j, int k, TileEntity tileentity);
}
