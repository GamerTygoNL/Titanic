package net.minecraft.src;

public interface IInventory {

    public abstract int getSizeInventory();

    public abstract ItemStack getStackInSlot(int i);

    public abstract ItemStack decrStackSize(int i, int j);

    public abstract void setInventorySlotContents(int i, ItemStack itemstack);

    public abstract String getInvName();

    public abstract int getInventoryStackLimit();

    public abstract void onInventoryChanged();

    public abstract boolean func_20070_a_(EntityPlayer entityplayer);
}
