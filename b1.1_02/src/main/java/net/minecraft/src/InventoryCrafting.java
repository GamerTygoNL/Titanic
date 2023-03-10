package net.minecraft.src;

public class InventoryCrafting
        implements IInventory {

    private ItemStack stackList[];
    private int nbrSlots;
    private CraftingInventoryCB eventHandler;

    public InventoryCrafting(CraftingInventoryCB craftinginventorycb, int i, int j) {
        nbrSlots = i * j;
        stackList = new ItemStack[nbrSlots];
        eventHandler = craftinginventorycb;
    }

    public int getSizeInventory() {
        return nbrSlots;
    }

    public ItemStack getStackInSlot(int i) {
        return stackList[i];
    }

    public String getInvName() {
        return "Crafting";
    }

    public ItemStack decrStackSize(int i, int j) {
        if (stackList[i] != null) {
            if (stackList[i].stackSize <= j) {
                ItemStack itemstack = stackList[i];
                stackList[i] = null;
                eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            ItemStack itemstack1 = stackList[i].splitStack(j);
            if (stackList[i].stackSize == 0) {
                stackList[i] = null;
            }
            eventHandler.onCraftMatrixChanged(this);
            return itemstack1;
        } else {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        stackList[i] = itemstack;
        eventHandler.onCraftMatrixChanged(this);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void onInventoryChanged() {
    }

    public boolean func_20070_a_(EntityPlayer entityplayer) {
        return true;
    }
}
