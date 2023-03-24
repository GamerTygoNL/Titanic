package cc.noxiuam.titanic.data.server.impl;

import cc.noxiuam.titanic.data.cosmetic.Cosmetic;
import cc.noxiuam.titanic.data.cosmetic.impl.alphaplace.*;
import cc.noxiuam.titanic.data.path.PathRegistry;
import cc.noxiuam.titanic.data.server.RegisteredServer;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class AlphaPlace implements RegisteredServer {

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
    public List<Cosmetic> getCosmetics() {
        return ImmutableList.of(
                new AdminCape(),
                new ModeratorCape(),
                new VeteranCape(),
                new DonatorCape(),
                new DefaultCape()
        );
    }

}
