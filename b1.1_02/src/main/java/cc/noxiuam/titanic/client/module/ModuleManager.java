package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.normal.ChunkLoadingFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerModelFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerAssetFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.SavingLevelFix;
import cc.noxiuam.titanic.client.module.impl.hud.impl.CoordinatesMod;
import cc.noxiuam.titanic.client.module.impl.normal.ModernInventory;
import cc.noxiuam.titanic.client.module.impl.normal.chat.AutoLogin;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatBundle;
import cc.noxiuam.titanic.client.module.impl.normal.overlay.BetaOverlay;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveBundle;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final CoordinatesMod coordinatesMod;

    private final ChatBundle chatBundle;
    private final PerspectiveBundle perspectiveBundle;
    private final BetaOverlay betaOverlay;
    private final ModernInventory modernInventory;
    private final AutoLogin autoLogin;

    public ModuleManager() {
        // qol mods
        this.mods.add(this.coordinatesMod = new CoordinatesMod());
        this.mods.add(this.chatBundle = new ChatBundle());
        this.mods.add(this.perspectiveBundle = new PerspectiveBundle());
        this.mods.add(this.betaOverlay = new BetaOverlay());
        this.mods.add(this.modernInventory = new ModernInventory());
        this.mods.add(this.autoLogin = new AutoLogin());

        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());
        this.mods.add(new ChunkLoadingFix());
    }

}
