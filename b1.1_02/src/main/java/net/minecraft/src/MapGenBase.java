package net.minecraft.src;

import java.util.Random;

public class MapGenBase {

    protected int field_1306_a;
    protected Random rand;

    public MapGenBase() {
        field_1306_a = 8;
        rand = new Random();
    }

    public void func_867_a(IChunkProvider ichunkprovider, World world, int i, int j, byte[] abyte0) {
        int k = field_1306_a;
        rand.setSeed(world.randomSeed);
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        for (int i1 = i - k; i1 <= i + k; i1++) {
            for (int j1 = j - k; j1 <= j + k; j1++) {
                rand.setSeed((long) i1 * l + (long) j1 * l1 ^ world.randomSeed);
                func_868_a(world, i1, j1, i, j, abyte0);
            }

        }

    }

    protected void func_868_a(World world, int i, int j, int k, int l, byte[] abyte0) {
    }
}
