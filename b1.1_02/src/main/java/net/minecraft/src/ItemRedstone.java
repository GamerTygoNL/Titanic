package net.minecraft.src;

public class ItemRedstone extends Item {

    public ItemRedstone(int i) {
        super(i);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
        if (l == 0) {
            j--;
        }
        if (l == 1) {
            j++;
        }
        if (l == 2) {
            k--;
        }
        if (l == 3) {
            k++;
        }
        if (l == 4) {
            i--;
        }
        if (l == 5) {
            i++;
        }
        if (!world.func_20084_d(i, j, k)) {
            return false;
        }
        if (Block.redstoneWire.canPlaceBlockAt(world, i, j, k)) {
            itemstack.stackSize--;
            world.setBlockWithNotify(i, j, k, Block.redstoneWire.blockID);
        }
        return true;
    }
}
