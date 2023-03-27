package cc.noxiuam.titanic.client.module.impl.normal;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.util.ItemStackUtil;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.event.impl.gui.SlotChangeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ported from a b1.1_02 mod, this is not my own in any way.
 */
public class ModernInventoryModule extends AbstractModule {

    private static final Logger LOGGER = new Logger("Modern Inventory Mod");

    private static final int MOVE_ONE_AND_STACK = 1;
    private static final int MOVE_ONE = 2;
    private static final int MOVE_STACK_AND_STACK = 3;
    private static final int MOVE_STACK = 4;
    private static final int MOVE_ALL_AND_STACK = 5;
    private static final int MOVE_ALL = 6;
    private static final int SORT = 7;
    private static final int NONE = 0;

    private static final Map<String, Integer> ACTION_MAP = new HashMap<>();

    private static boolean wroteConfig = false;

    private static int altAlternative = 0;

    public ModernInventoryModule() {
        super("modernInventory", "Modern Inventory", false);
        this.addEvent(SlotChangeEvent.class, this::onSlotChange);
        this.writeModuleConfig();
    }

    private void onSlotChange(SlotChangeEvent event) {
        event.cancel();
    }

    @Override
    public void writeModuleConfig() {
        if (!Ref.getConfigManager().isSetup()) {
            return;
        }

        try {
            File config = new File(
                    Ref.getConfigManager().getThirdPartyModsConfigDir()
                            + File.separator +
                            "modernInventory.cfg"
            );
            if (config.exists()) {
                BufferedReader var1 = new BufferedReader(new FileReader(config));

                String line;
                while((line = var1.readLine()) != null) {
                    if (line.startsWith("MOVE_ONE =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_ONE);
                    } else if (line.startsWith("MOVE_ONE_AND_STACK =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_ONE_AND_STACK);
                    } else if (line.startsWith("MOVE_STACK =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_STACK);
                    } else if (line.startsWith("MOVE_STACK_AND_STACK =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_STACK_AND_STACK);
                    } else if (line.startsWith("MOVE_ALL =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_ALL);
                    } else if (line.startsWith("MOVE_ALL_AND_STACK =")) {
                        putOnKeyMap(ACTION_MAP, line, MOVE_ALL_AND_STACK);
                    } else if (line.startsWith("SORT =")) {
                        putOnKeyMap(ACTION_MAP, line, SORT);
                    } else if (line.startsWith("USEINSTEADOFALT =")) {
                        try {
                            altAlternative = Integer.parseInt(line.split("=")[1].trim());
                        } catch (Exception ignored) {
                        }
                    }
                }

                var1.close();
            } else {
                ACTION_MAP.put("tff0", MOVE_ONE_AND_STACK);
                ACTION_MAP.put("tff1", MOVE_ONE);
                ACTION_MAP.put("ftf0", MOVE_STACK_AND_STACK);
                ACTION_MAP.put("ftf1", MOVE_STACK);
                ACTION_MAP.put("fft0", MOVE_ALL_AND_STACK);
                ACTION_MAP.put("fft1", MOVE_ALL);
                ACTION_MAP.put("fff2", SORT);
                BufferedWriter var6 = new BufferedWriter(new FileWriter(config));
                var6.write("# Configure key/mouse combinations for actions:");
                var6.newLine();
                var6.write("# Seperate multiple combinations with ,");
                var6.newLine();
                var6.write("# the values describe CTRL, SHIFT, ALT, Mousebutton in this order");
                var6.newLine();
                var6.write("# t means the button is pressed, f means it isn't pressed");
                var6.newLine();
                var6.write("# 0 = left mousebutton, 1 = right mousebutton, 2 = middle mousebutton");
                var6.newLine();
                var6.write("# Example: If you want the event of CTRL, SHIFT + left Mousebutton, it would be ttf0");
                var6.newLine();
                var6.write(" ");
                var6.newLine();
                var6.write("MOVE_ONE = tff1");
                var6.newLine();
                var6.write("MOVE_ONE_AND_STACK = tff0");
                var6.newLine();
                var6.write("MOVE_STACK = ftf1");
                var6.newLine();
                var6.write("MOVE_STACK_AND_STACK = ftf0");
                var6.newLine();
                var6.write("MOVE_ALL = fft1");
                var6.newLine();
                var6.write("MOVE_ALL_AND_STACK = fft0");
                var6.newLine();
                var6.write("SORT = fff2");
                var6.newLine();
                var6.newLine();
                var6.write("# Uncomment to use the Space key instead of the ALT key");
                var6.newLine();
                var6.write("#USEINSTEADOFALT = 57");
                var6.newLine();
                var6.flush();
                var6.close();
            }
        } catch (Exception var5) {
            System.out.println(var5.getMessage());
        }

        wroteConfig = true;
    }

    public void handleClickOnSlot(int i, int button, Minecraft mc, CraftingInventoryCB inventoryCB) {
        if (!wroteConfig) {
            this.writeModuleConfig();
        }

        if (mc.thePlayer != null) {
            if (i < 0) {
                mc.playerController.func_20085_a(inventoryCB.unusedList, i, button, mc.thePlayer);
            } else {
                boolean controlKeyDown = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
                boolean shiftKeyDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
                boolean menuKeyDown;
                if (altAlternative != 0) {
                    menuKeyDown = Keyboard.isKeyDown(altAlternative);
                } else {
                    menuKeyDown = Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
                }
                Integer action = getAction(controlKeyDown, shiftKeyDown, menuKeyDown, button);
                List list = mc.thePlayer.field_20068_h.field_20122_e;
                int k;
                int l;
                int i1;
                int j1 = i1 = k = l = list.size() - (mc.thePlayer.inventory.getSizeInventory() - 4);
                if (i < k) {
                    l = list.size();
                    j1 = 0;
                } else {
                    if (mc.thePlayer.field_20068_h instanceof CraftingInventoryPlayerCB) {
                        l -= 4;
                    }
                    k = 0;
                    i1 = list.size();
                }
                if (action == NONE) {
                    mc.playerController.func_20085_a(inventoryCB.unusedList, i, button, mc.thePlayer);
                } else if (action == SORT) {
                    int k1;
                    int i2 = k1 = list.size() - (mc.thePlayer.inventory.getSizeInventory() - 4);
                    if (i < i2) {
                        i2 = 0;
                    } else if (i < list.size() - 9) {
                        k1 = list.size() - 9;
                    } else {
                        k1 = list.size();
                        i2 = k1 - 9;
                    }
                    sortInventory(mc, inventoryCB, i2, k1);
                } else if (action != MOVE_ONE_AND_STACK && action != MOVE_ONE) {
                    if (action != MOVE_STACK && action != MOVE_STACK_AND_STACK) {
                        if (action == MOVE_ALL || action == MOVE_ALL_AND_STACK) {
                            int l1 = -1;
                            int j2 = -1;
                            ItemStack itemstack = ((Slot) list.get(i)).getStack();
                            if (itemstack != null) {
                                l1 = itemstack.itemID;
                                j2 = ItemStackUtil.getItemDamage(itemstack);
                            }
                            if (l1 == -1) {
                                return;
                            }
                            boolean flag3 = false;
                            while (mc.thePlayer.inventory.func_20075_i() == null && !flag3) {
                                flag3 = true;
                                for (int l2 = j1; l2 < i1; l2++) {
                                    ItemStack itemstack2 = ((Slot) list.get(l2)).getStack();
                                    if (itemstack2 != null
                                            && itemstack2.itemID == l1
                                            && ItemStackUtil.getItemDamage(itemstack2) == j2
                                            && hasFreeSlot(list, k, l, l1, j2, itemstack2.stackSize, action == MOVE_ALL_AND_STACK)) {
                                        mc.playerController.func_20085_a(inventoryCB.unusedList, l2, 0, mc.thePlayer);
                                        l2 = i1;
                                        flag3 = false;
                                    }
                                }

                                if (action == MOVE_ALL) {
                                    dropIntoFreeSlot(mc, inventoryCB, list, k, l, true);
                                } else if (action == MOVE_ALL_AND_STACK) {
                                    func_20010_a(mc, inventoryCB, list, k, l, true);
                                }
                            }
                        } else {
                            LOGGER.error("Something is broken.");
                        }
                    } else {
                        if (list.get(i) instanceof SlotCrafting) {
                            ItemStack itemstack1 = null;
                            boolean flag4 = false;
                            int i3 = 0;
                            int k2;
                            do {
                                i3++;
                                k2 = itemstack1 == null ? 0 : itemstack1.stackSize;
                                mc.playerController.func_20085_a(inventoryCB.unusedList, i, 0, mc.thePlayer);
                                itemstack1 = mc.thePlayer.inventory.func_20075_i();
                            } while (itemstack1 != null && k2 < itemstack1.stackSize && i3 < 70);
                        } else {
                            mc.playerController.func_20085_a(inventoryCB.unusedList, i, 0, mc.thePlayer);
                        }
                        if (action == MOVE_STACK) {
                            dropIntoFreeSlot(mc, inventoryCB, list, k, l, true);
                        } else if (action == MOVE_STACK_AND_STACK) {
                            func_20010_a(mc, inventoryCB, list, k, l, true);
                        }
                        if (mc.thePlayer.inventory.func_20075_i() != null && !(list.get(i) instanceof SlotCrafting)) {
                            mc.playerController.func_20085_a(inventoryCB.unusedList, i, 0, mc.thePlayer);
                        }
                    }
                } else {
                    mc.playerController.func_20085_a(inventoryCB.unusedList, i, 0, mc.thePlayer);
                    if (action == MOVE_ONE) {
                        dropIntoFreeSlot(mc, inventoryCB, list, k, l, false);
                    } else if (action == MOVE_ONE_AND_STACK) {
                        func_20010_a(mc, inventoryCB, list, k, l, false);
                    }
                    if (mc.thePlayer.inventory.func_20075_i() != null && !(list.get(i) instanceof SlotCrafting)) {
                        mc.playerController.func_20085_a(inventoryCB.unusedList, i, 0, mc.thePlayer);
                    }
                }
            }
        }
    }

    private void dropIntoFreeSlot(Minecraft mc, CraftingInventoryCB inventoryCB, List list, int i, int j, boolean flag) {
        ItemStack itemstack = mc.thePlayer.inventory.func_20075_i();

        for (int k = i; k < j && itemstack != null && itemstack.stackSize > 0; k++) {
            Slot slot = (Slot) list.get(k);
            ItemStack itemstack1 = slot == null ? null : slot.getStack();
            if (slot != null && itemstack1 == null && !(slot instanceof SlotCrafting) && slot.isItemValid(itemstack)) {
                if (!flag) {
                    mc.playerController.func_20085_a(inventoryCB.unusedList, k, 1, mc.thePlayer);
                    return;
                }
                mc.playerController.func_20085_a(inventoryCB.unusedList, k, 0, mc.thePlayer);
            }
            itemstack = mc.thePlayer.inventory.func_20075_i();
        }

    }

    private boolean hasFreeSlot(List list, int i, int j, int k, int l, int i1, boolean flag) {
        for (int j1 = i; j1 < j; j1++) {
            Slot slot = (Slot) list.get(j1);
            ItemStack itemstack = slot == null ? null : slot.getStack();
            if (slot != null && itemstack == null && !(slot instanceof SlotCrafting)) {
                return true;
            }
        }

        for (int k1 = i; k1 < j && flag; k1++) {
            Slot slot1 = (Slot) list.get(k1);
            ItemStack itemstack1 = slot1 == null ? null : slot1.getStack();
            if (slot1 != null && itemstack1 != null 
                    && itemstack1.itemID == k 
                    && ItemStackUtil.getItemDamage(itemstack1) == l
                    && itemstack1.stackSize + i1 <= itemstack1.getMaxStackSize() 
                    && !(slot1 instanceof SlotCrafting)) {
                return true;
            }
        }

        return false;
    }

    private void func_20010_a(Minecraft mc, CraftingInventoryCB inventoryCB, List list, int i, int j, boolean flag) {
        ItemStack itemstack = mc.thePlayer.inventory.func_20075_i();
        for (int k = i; k < j && itemstack != null && itemstack.stackSize > 0; k++) {
            Slot slot = (Slot) list.get(k);
            ItemStack itemstack1 = slot == null ? null : slot.getStack();
            if (itemstack1 != null && !(slot instanceof SlotCrafting) && itemstack1.func_20108_a(itemstack) && itemstack1.getMaxStackSize() > itemstack1.stackSize) {
                if (!flag) {
                    mc.playerController.func_20085_a(inventoryCB.unusedList, k, 1, mc.thePlayer);
                    return;
                }
                mc.playerController.func_20085_a(inventoryCB.unusedList, k, 0, mc.thePlayer);
            }
            itemstack = mc.thePlayer.inventory.func_20075_i();
        }

        dropIntoFreeSlot(mc, inventoryCB, list, i, j, flag);
    }

    private void sortInventory(Minecraft mc, CraftingInventoryCB inventoryCB, int i, int j) {
        if (mc.thePlayer.inventory.func_20075_i() == null) {
            List list = mc.thePlayer.field_20068_h.field_20122_e;
            if (list.get(i) instanceof SlotCrafting) {
                i++;
            }
            for (int k = i; k < j; k++) {
                ItemStack itemstack = ((Slot) list.get(k)).getStack();
                if (itemstack == null || itemstack.stackSize >= itemstack.getMaxStackSize()) {
                    continue;
                }
                for (int j1 = j - 1; j1 > k; j1--) {
                    ItemStack itemstack2 = ((Slot) list.get(j1)).getStack();
                    if (itemstack2 == null 
                            || itemstack2.stackSize >= itemstack2.getMaxStackSize() 
                            || itemstack2.itemID != itemstack.itemID 
                            || ItemStackUtil.getItemDamage(itemstack2) != ItemStackUtil.getItemDamage(itemstack)) {
                        continue;
                    }
                    mc.playerController.func_20085_a(inventoryCB.unusedList, j1, 0, mc.thePlayer);
                    mc.playerController.func_20085_a(inventoryCB.unusedList, k, 0, mc.thePlayer);
                    if (mc.thePlayer.inventory.func_20075_i() != null) {
                        mc.playerController.func_20085_a(inventoryCB.unusedList, j1, 0, mc.thePlayer);
                    }
                    if (((Slot) list.get(k)).getStack().stackSize >= ((Slot) list.get(k)).getSlotStackLimit()) {
                        j1 = k;
                    }
                }

            }

            for (int l = i; l < j - 1; l++) {
                boolean flag = true;
                ItemStack itemstack4 = ((Slot) list.get(l)).getStack();
                for (int l1 = l + 1; l1 < j; l1++) {
                    ItemStack itemstack5 = ((Slot) list.get(l1)).getStack();
                    if (lowerThan(itemstack5, itemstack4)) {
                        flag = false;
                    }
                }

                if (flag) {
                    continue;
                }
                mc.playerController.func_20085_a(inventoryCB.unusedList, l, 0, mc.thePlayer);
                int i2 = l + 1;
                for (int j2 = 0; i2 != l && j2 < 100; j2++) {
                    ItemStack itemstack6 = mc.thePlayer.inventory.func_20075_i();
                    i2 = l;
                    for (int k2 = l + 1; k2 < j; k2++) {
                        if (lowerThan(((Slot) list.get(k2)).getStack(), itemstack6)) {
                            i2++;
                        }
                    }

                    for (; i2 < j && equalTo(((Slot) list.get(i2)).getStack(), itemstack6); i2++) {
                    }
                    if (i2 < j) {
                        mc.playerController.func_20085_a(inventoryCB.unusedList, i2, 0, mc.thePlayer);
                    }
                }

            }

            for (int i1 = i; i1 < j; i1++) {
                ItemStack itemstack1 = ((Slot) list.get(i1)).getStack();
                if (itemstack1 == null || itemstack1.stackSize >= itemstack1.getMaxStackSize()) {
                    continue;
                }
                for (int k1 = j - 1; k1 > i1; k1--) {
                    ItemStack itemstack3 = ((Slot) list.get(k1)).getStack();
                    if (itemstack3 == null
                            || itemstack3.itemID != itemstack1.itemID
                            || ItemStackUtil.getItemDamage(itemstack3) != ItemStackUtil.getItemDamage(itemstack1)) {
                        continue;
                    }
                    mc.playerController.func_20085_a(inventoryCB.unusedList, k1, 0, mc.thePlayer);
                    mc.playerController.func_20085_a(inventoryCB.unusedList, i1, 0, mc.thePlayer);
                    if (mc.thePlayer.inventory.func_20075_i() != null) {
                        mc.playerController.func_20085_a(inventoryCB.unusedList, k1, 0, mc.thePlayer);
                    }
                    if (((Slot) list.get(i1)).getStack().stackSize >= ((Slot) list.get(i1)).getSlotStackLimit()) {
                        k1 = i1;
                    }
                }

            }

        }
    }

    private void putOnKeyMap(Map<String, Integer> map, String value, Integer action) {
        String[] splitByEquals = value.split("=");
        if (splitByEquals.length > 1) {
            String[] finalValueSplit = splitByEquals[1].split(",");

            for (String split : finalValueSplit) {
                map.put(split.trim(), action);
            }
        }
    }

    public Integer getAction(boolean var0, boolean var1, boolean var2, int var3) {
        Integer action = ACTION_MAP.get((var0 ? "t" : "f") + (var1 ? "t" : "f") + (var2 ? "t" : "f") + var3);
        if (action == null) {
            action = NONE;
        }

        return action;
    }

    private boolean lowerThan(ItemStack stack1, ItemStack stack2) {
        return stack1 != null && stack2 == null
                || stack1 != null && (stack1.itemID < stack2.itemID
                || stack1.itemID == stack2.itemID && stack1.getItem().shiftedIndex > 1
                && ItemStackUtil.getItemDamage(stack1) < ItemStackUtil.getItemDamage(stack2));
    }

    private boolean equalTo(ItemStack stack1, ItemStack stack2) {
        return stack1 == null && stack2 == null
                || (stack1 != null && stack2 != null && (stack1.itemID == stack2.itemID && (stack1.getItem().shiftedIndex == 1
                || ItemStackUtil.getItemDamage(stack1) == ItemStackUtil.getItemDamage(stack2))));
    }

}
