package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.impl.fix.impl.*;
import cc.noxiuam.titanic.client.module.impl.hud.impl.LightLevelModule;
import cc.noxiuam.titanic.client.module.impl.normal.ChunkLoadingFix;
import cc.noxiuam.titanic.client.module.impl.hud.impl.CoordinatesModule;
import cc.noxiuam.titanic.client.module.impl.normal.ModernInventoryModule;
import cc.noxiuam.titanic.client.module.impl.normal.NametagEditorModule;
import cc.noxiuam.titanic.client.module.impl.normal.ScreenshotModule;
import cc.noxiuam.titanic.client.module.impl.normal.chat.AutoLoginModule;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatEditorModule;
import cc.noxiuam.titanic.client.module.impl.normal.gui.MenuTweaksModule;
import cc.noxiuam.titanic.client.module.impl.normal.performance.PerformanceModule;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveModule;
import cc.noxiuam.titanic.client.module.impl.normal.zoom.ZoomModule;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final CoordinatesModule coordinatesModule;
    private final LightLevelModule lightLevelModule;

    private final ChatEditorModule chatEditorModule;
    private final PerspectiveModule perspectiveModule;
    private final MenuTweaksModule menuTweaksModule;
    private final ModernInventoryModule modernInventoryModule;
    private final AutoLoginModule autoLoginModule;
    private final NametagEditorModule nametagEditorModule;
    private final PerformanceModule performanceModule;
    private final ScreenshotModule screenshotModule;
    private final ZoomModule zoomModule;

    public ModuleManager() {
        // qol mods
        this.mods.add(this.coordinatesModule = new CoordinatesModule());
        this.mods.add(this.modernInventoryModule = new ModernInventoryModule());
        this.mods.add(this.screenshotModule = new ScreenshotModule());
        this.mods.add(this.performanceModule = new PerformanceModule());
        this.mods.add(this.zoomModule = new ZoomModule());

        this.mods.add(this.autoLoginModule = new AutoLoginModule());
        this.mods.add(this.menuTweaksModule = new MenuTweaksModule());
        this.mods.add(this.perspectiveModule = new PerspectiveModule());
        this.mods.add(this.chatEditorModule = new ChatEditorModule());
        this.mods.add(this.nametagEditorModule = new NametagEditorModule());

        this.mods.add(this.lightLevelModule = new LightLevelModule());
        this.mods.add(new ChunkLoadingFix());

        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());
        this.mods.add(new ChestTextFix());
        this.mods.add(new DeadEntityRenderFix());
    }

}
