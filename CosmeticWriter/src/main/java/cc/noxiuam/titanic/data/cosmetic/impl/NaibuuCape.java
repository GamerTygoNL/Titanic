package cc.noxiuam.titanic.data.cosmetic.impl;

import cc.noxiuam.titanic.data.cosmetic.Cosmetic;
import cc.noxiuam.titanic.data.path.PathRegistry;

public class NaibuuCape implements Cosmetic {

    @Override
    public String getName() {
        return "Naibuu";
    }

    @Override
    public String getDescription() {
        return "Given to Titanic's designer, Naibuu.";
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
        return PathRegistry.CAPE_DIR + "HS50.png";
    }

}
