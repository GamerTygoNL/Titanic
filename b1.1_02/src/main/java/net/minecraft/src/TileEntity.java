package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {

    private static final Map nameToClassMap = new HashMap();
    private static final Map classToNameMap = new HashMap();

    static {
        addMapping(TileEntityFurnace.class, "Furnace");
        addMapping(TileEntityChest.class, "Chest");
        addMapping(TileEntitySign.class, "Sign");
        addMapping(TileEntityMobSpawner.class, "MobSpawner");
    }

    public World worldObj;
    public int xCoord;
    public int yCoord;
    public int zCoord;

    public TileEntity() {
    }

    private static void addMapping(Class class1, String s) {
        if (classToNameMap.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            nameToClassMap.put(s, class1);
            classToNameMap.put(class1, s);
        }
    }

    public static TileEntity createAndLoadEntity(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;
        try {
            Class class1 = (Class) nameToClassMap.get(nbttagcompound.getString("id"));
            if (class1 != null) {
                tileentity = (TileEntity) class1.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (tileentity != null) {
            tileentity.readFromNBT(nbttagcompound);
        } else {
            System.out.println("Skipping TileEntity with id " + nbttagcompound.getString("id"));
        }
        return tileentity;
    }

    static Class _mthclass$(String s) {
        try {
            return Class.forName(s);
        } catch (ClassNotFoundException classnotfoundexception) {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        xCoord = nbttagcompound.getInteger("x");
        yCoord = nbttagcompound.getInteger("y");
        zCoord = nbttagcompound.getInteger("z");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        String s = (String) classToNameMap.get(getClass());
        if (s == null) {
            throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.setString("id", s);
            nbttagcompound.setInteger("x", xCoord);
            nbttagcompound.setInteger("y", yCoord);
            nbttagcompound.setInteger("z", zCoord);
        }
    }

    public void updateEntity() {
    }

    public int getBlockMetadata() {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    public void onInventoryChanged() {
        if (worldObj != null) {
            worldObj.func_698_b(xCoord, yCoord, zCoord, this);
        }
    }

    public double getDistanceFrom(double d, double d1, double d2) {
        double d3 = ((double) xCoord + 0.5D) - d;
        double d4 = ((double) yCoord + 0.5D) - d1;
        double d5 = ((double) zCoord + 0.5D) - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public Block getBlockType() {
        return Block.blocksList[worldObj.getBlockId(xCoord, yCoord, zCoord)];
    }
}
