package net.minecraft.src;

public class ItemAxe extends ItemTool {

    private static Block blocksEffectiveAgainst[];

    static {
        blocksEffectiveAgainst = (new Block[]{
                Block.planks, Block.bookShelf, Block.wood, Block.crate
        });
    }

    public ItemAxe(int i, int j) {
        super(i, 3, j, blocksEffectiveAgainst);
    }
}
