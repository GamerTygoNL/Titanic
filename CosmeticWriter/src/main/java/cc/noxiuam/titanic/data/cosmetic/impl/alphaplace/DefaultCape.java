package cc.noxiuam.titanic.data.cosmetic.impl.alphaplace;

import cc.noxiuam.titanic.CosmeticWriter;
import cc.noxiuam.titanic.data.cosmetic.Cosmetic;

public class DefaultCape implements Cosmetic {

    @Override
    public String getName() {
        return "AlphaPlace Default";
    }

    @Override
    public String getDescription() {
        return "Given to regulars on AlphaPlace.";
    }

    @Override
    public boolean isEquippedByDefault() {
        return true;
    }

    @Override
    public String getType() {
        return "cape";
    }

    @Override
    public String getLocation() {
        return CosmeticWriter.getInstance()
                .getServerManager()
                .getAlphaPlace()
                .getCosmeticPath() + "default.png";
    }

}
