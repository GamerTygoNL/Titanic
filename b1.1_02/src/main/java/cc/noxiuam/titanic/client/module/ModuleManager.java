package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerModelFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerAssetFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.SavingLevelFix;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatBundle;
import cc.noxiuam.titanic.client.module.impl.normal.overlay.BetaOverlay;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveBundle;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final ChatBundle chatBundle;

    public ModuleManager() {
        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());

        // qol mods
        this.mods.add(chatBundle = new ChatBundle());
        this.mods.add(new PerspectiveBundle());
        this.mods.add(new BetaOverlay());

        for (AbstractModule module : mods) {
            if (module.enabledByDefault) {
                module.addAllEvents();
            }
        }
    }

}
