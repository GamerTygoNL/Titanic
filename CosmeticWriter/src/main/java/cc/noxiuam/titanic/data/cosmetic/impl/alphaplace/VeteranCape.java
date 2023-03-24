package cc.noxiuam.titanic.data.cosmetic.impl.alphaplace;

import cc.noxiuam.titanic.CosmeticWriter;
import cc.noxiuam.titanic.data.cosmetic.Cosmetic;

public class VeteranCape implements Cosmetic {

    @Override
    public String getName() {
        return "AlphaPlace Veteran";
    }

    @Override
    public String getDescription() {
        return "Given to players that have been on AlphaPlace for 2+ years.";
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
                .getCosmeticPath() + "veteran.png";
    }

}
