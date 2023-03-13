package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerModelFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerAssetFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.SavingLevelFix;
import cc.noxiuam.titanic.client.module.impl.normal.ModernInventory;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatBundle;
import cc.noxiuam.titanic.client.module.impl.normal.overlay.ModernOverlay;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveBundle;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final ChatBundle chatBundle;
    private final ModernInventory modernInventory;

    public ModuleManager() {
        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());

        // qol mods
        this.mods.add(chatBundle = new ChatBundle());
        this.mods.add(new PerspectiveBundle());
        this.mods.add(new ModernOverlay());
        this.mods.add(this.modernInventory = new ModernInventory());

        for (AbstractModule module : mods) {
            if (module.enabledByDefault) {
                module.addAllEvents();
            }
        }
    }

}
