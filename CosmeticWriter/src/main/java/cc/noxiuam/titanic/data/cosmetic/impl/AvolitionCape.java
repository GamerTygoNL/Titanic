package cc.noxiuam.titanic.data.cosmetic.impl;

import cc.noxiuam.titanic.data.cosmetic.Cosmetic;
import cc.noxiuam.titanic.data.path.PathRegistry;

public class AvolitionCape implements Cosmetic {

    @Override
    public String getName() {
        return "Avolition";
    }

    @Override
    public String getDescription() {
        return "Only given to original Team Avolition members, and Nox since he grew up with their videos.";
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
        return PathRegistry.CAPE_DIR + "avolition.png";
    }

}
