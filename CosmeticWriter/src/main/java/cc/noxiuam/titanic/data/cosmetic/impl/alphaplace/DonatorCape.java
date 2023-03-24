package cc.noxiuam.titanic.data.cosmetic.impl.alphaplace;

import cc.noxiuam.titanic.CosmeticWriter;
import cc.noxiuam.titanic.data.cosmetic.ICosmetic;

public class DonatorCape implements ICosmetic {

    @Override
    public String getName() {
        return "AlphaPlace Donator";
    }

    @Override
    public String getDescription() {
        return "Given to people who donate to AlphaPlace.";
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
                .getCosmeticPath() + "donator.png";
    }

}
