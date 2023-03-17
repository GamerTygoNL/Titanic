package cc.noxiuam.titanic.client.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public int clamp_int(int par0, int par1, int par2) {
        return par0 < par1 ? par1 : (Math.min(par0, par2));
    }

    public float clamp_float(float par0, float par1, float par2) {
        return par0 < par1 ? par1 : (Math.min(par0, par2));
    }

    public double clamp_double(double p_151237_0_, double p_151237_2_, double p_151237_4_) {
        return p_151237_0_ < p_151237_2_ ? p_151237_2_ : (Math.min(p_151237_0_, p_151237_4_));
    }

}
