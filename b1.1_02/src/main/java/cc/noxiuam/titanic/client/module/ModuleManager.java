package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerModelFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerAssetFix;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {

    private final List<AbstractModule> playerModules = new CopyOnWriteArrayList<>();

    public ModuleManager() {
        this.playerModules.add(new PlayerAssetFix());
        this.playerModules.add(new PlayerModelFix());
    }

}
