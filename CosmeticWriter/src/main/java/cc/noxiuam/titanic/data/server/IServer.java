package cc.noxiuam.titanic.data.server;

import cc.noxiuam.titanic.data.cosmetic.ICosmetic;

import java.util.List;

public interface IServer {

    String getCosmeticPath();

    List<String> getAddresses();

    List<ICosmetic> getCosmetics();

}
