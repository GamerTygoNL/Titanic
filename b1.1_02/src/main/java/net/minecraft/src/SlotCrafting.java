package net.minecraft.src;

public class SlotCrafting extends Slot {

    private final IInventory craftMatrix;

    public SlotCrafting(IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        craftMatrix = iinventory;
    }

    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }

    public void onPickupFromSlot() {
        for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
            if (craftMatrix.getStackInSlot(i) != null) {
                craftMatrix.decrStackSize(i, 1);
            }
        }

    }
}
