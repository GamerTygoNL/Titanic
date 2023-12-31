package net.minecraft.src;

import java.util.Comparator;

public class RenderSorter
        implements Comparator {

    private EntityPlayer field_4274_a;

    public RenderSorter(EntityPlayer entityplayer) {
        field_4274_a = entityplayer;
    }

    public int func_993_a(WorldRenderer worldrenderer, WorldRenderer worldrenderer1) {
        boolean flag = worldrenderer.isInFrustrum;
        boolean flag1 = worldrenderer1.isInFrustrum;
        if (flag && !flag1) {
            return 1;
        }
        if (flag1 && !flag) {
            return -1;
        }
        double d = worldrenderer.distanceToEntity(field_4274_a);
        double d1 = worldrenderer1.distanceToEntity(field_4274_a);
        if (d < d1) {
            return 1;
        }
        if (d > d1) {
            return -1;
        } else {
            return worldrenderer.field_1735_w >= worldrenderer1.field_1735_w ? -1 : 1;
        }
    }

    public int compare(Object obj, Object obj1) {
        return func_993_a((WorldRenderer) obj, (WorldRenderer) obj1);
    }
}
