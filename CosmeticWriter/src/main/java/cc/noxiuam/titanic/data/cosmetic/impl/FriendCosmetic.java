package cc.noxiuam.titanic.data.cosmetic.impl;

import cc.noxiuam.titanic.data.cosmetic.Cosmetic;
import cc.noxiuam.titanic.data.path.PathRegistry;

public class FriendCosmetic implements Cosmetic {

    @Override
    public String getName() {
        return "Friend";
    }

    @Override
    public String getDescription() {
        return "Only given to friends of owners of Titanic.";
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
        return PathRegistry.CAPE_DIR + "friend.png";
    }

}
