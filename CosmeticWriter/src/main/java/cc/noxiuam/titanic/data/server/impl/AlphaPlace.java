package cc.noxiuam.titanic.data.server.impl;

import cc.noxiuam.titanic.data.cosmetic.ICosmetic;
import cc.noxiuam.titanic.data.cosmetic.impl.alphaplace.*;
import cc.noxiuam.titanic.data.path.PathRegistry;
import cc.noxiuam.titanic.data.server.IServer;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class AlphaPlace implements IServer {

    @Override
    public String getCosmeticPath() {
        return PathRegistry.SERVERS_CAPE_PATH + "alphaplace/";
    }

    @Override
    public List<String> getAddresses() {
        return ImmutableList.of(
                "alpha.place",
                "alphaplace.net",
                "alphaplace.tk"
        );
    }

    @Override
    public List<ICosmetic> getCosmetics() {
        return ImmutableList.of(
                new AdminCape(),
                new ModeratorCape(),
                new VeteranCape(),
                new DonatorCape(),
                new DefaultCape()
        );
    }

}
