package net.minecraft.src;

public enum EnumSkyBlock {
    Sky(15),
    Block(0);

    /*
        public static final EnumSkyBlock Sky;
        public static final EnumSkyBlock Block;
    */
    public final int field_1722_c;

    /*
        public static EnumSkyBlock[] values()
        {
            return (EnumSkyBlock[])field_1721_d.clone();
        }

        public static EnumSkyBlock valueOf(String s)
        {
            return (EnumSkyBlock)Enum.valueOf(EnumSkyBlock.class, s);
        }
    */
    private EnumSkyBlock(int j) {
        field_1722_c = j;
    }
/*
    private static final EnumSkyBlock field_1721_d[]; /* synthetic field */
/*
    static 
    {
        Sky = new EnumSkyBlock("Sky", 0, 15);
        Block = new EnumSkyBlock("Block", 1, 0);
        field_1721_d = (new EnumSkyBlock[] {
            Sky, Block
        });
    }
*/
}
