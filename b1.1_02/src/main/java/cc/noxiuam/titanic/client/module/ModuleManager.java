package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.fix.impl.ChestTextFix;
import cc.noxiuam.titanic.client.module.impl.normal.ChunkLoadingFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerModelFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.PlayerAssetFix;
import cc.noxiuam.titanic.client.module.impl.fix.impl.SavingLevelFix;
import cc.noxiuam.titanic.client.module.impl.hud.impl.CoordinatesMod;
import cc.noxiuam.titanic.client.module.impl.normal.ModernInventoryModule;
import cc.noxiuam.titanic.client.module.impl.normal.NametagEditorModule;
import cc.noxiuam.titanic.client.module.impl.normal.chat.AutoLoginModule;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatModule;
import cc.noxiuam.titanic.client.module.impl.normal.overlay.OverlayModule;
import cc.noxiuam.titanic.client.module.impl.normal.performance.PerformanceModule;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveModule;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final CoordinatesMod coordinatesMod;

    private final ChatModule chatModule;
    private final PerspectiveModule perspectiveModule;
    private final OverlayModule overlayModule;
    private final ModernInventoryModule modernInventoryModule;
    private final AutoLoginModule autoLoginModule;
    private final NametagEditorModule nametagEditorModule;
    private final PerformanceModule performanceModule;

    public ModuleManager() {
        // qol mods
        this.mods.add(this.coordinatesMod = new CoordinatesMod());
        this.mods.add(this.chatModule = new ChatModule());
        this.mods.add(this.perspectiveModule = new PerspectiveModule());
        this.mods.add(this.overlayModule = new OverlayModule());
        this.mods.add(this.modernInventoryModule = new ModernInventoryModule());
        this.mods.add(this.autoLoginModule = new AutoLoginModule());
        this.mods.add(this.nametagEditorModule = new NametagEditorModule());
        this.mods.add(this.performanceModule = new PerformanceModule());

        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());
        this.mods.add(new ChunkLoadingFix());
        this.mods.add(new ChestTextFix());
    }

}
