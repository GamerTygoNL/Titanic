package cc.noxiuam.titanic.data.server;

import cc.noxiuam.titanic.data.cosmetic.Cosmetic;

import java.util.List;

public interface RegisteredServer {

    String getCosmeticPath();

    List<String> getAddresses();

    List<Cosmetic> getCosmetics();

}
