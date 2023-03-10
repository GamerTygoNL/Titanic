package net.minecraft.src;

public class ItemSeeds extends Item {

    private int field_318_a;

    public ItemSeeds(int i, int j) {
        super(i);
        field_318_a = j;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        }
        int i1 = world.getBlockId(i, j, k);
        if (i1 == Block.tilledField.blockID) {
            world.setBlockWithNotify(i, j + 1, k, field_318_a);
            itemstack.stackSize--;
            return true;
        } else {
            return false;
        }
    }
}
