package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.module.external.ThirdPartyLoader;
import cc.noxiuam.titanic.client.module.impl.fix.impl.*;
import cc.noxiuam.titanic.client.module.impl.hud.impl.FPSModule;
import cc.noxiuam.titanic.client.module.impl.hud.impl.FofoModule;
import cc.noxiuam.titanic.client.module.impl.hud.impl.LightLevelModule;
import cc.noxiuam.titanic.client.module.impl.normal.world.ChunkLoadingFix;
import cc.noxiuam.titanic.client.module.impl.hud.impl.CoordinatesModule;
import cc.noxiuam.titanic.client.module.impl.normal.gui.ModernInventoryModule;
import cc.noxiuam.titanic.client.module.impl.normal.NametagEditorModule;
import cc.noxiuam.titanic.client.module.impl.normal.ScreenshotModule;
import cc.noxiuam.titanic.client.module.impl.normal.gui.chat.AutoLoginModule;
import cc.noxiuam.titanic.client.module.impl.normal.gui.chat.ChatEditorModule;
import cc.noxiuam.titanic.client.module.impl.normal.gui.PackTweaksModule;
import cc.noxiuam.titanic.client.module.impl.normal.world.WorldEditor;
import cc.noxiuam.titanic.client.module.impl.normal.world.performance.PerformanceModule;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.PerspectiveModule;
import cc.noxiuam.titanic.client.module.impl.normal.perspective.zoom.ZoomModule;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModuleManager {

    private final List<AbstractModule> mods = new CopyOnWriteArrayList<>();

    private final ThirdPartyLoader thirdPartyLoader;

    private final CoordinatesModule coordinatesModule;
    private final LightLevelModule lightLevelModule;
    private final FPSModule fpsModule;
    private final FofoModule fofoModule;

    private final ChatEditorModule chatEditorModule;
    private final PerspectiveModule perspectiveModule;
    private final PackTweaksModule packTweaksModule;
    private final WorldEditor worldEditor;
    private final ModernInventoryModule modernInventoryModule;
    private final AutoLoginModule autoLoginModule;
    private final NametagEditorModule nametagEditorModule;
    private final PerformanceModule performanceModule;
    private final ScreenshotModule screenshotModule;
    private final ZoomModule zoomModule;

    public ModuleManager() {
        this.thirdPartyLoader = new ThirdPartyLoader();

        // qol mods
        this.mods.add(this.modernInventoryModule = new ModernInventoryModule());
        this.mods.add(this.performanceModule = new PerformanceModule());
        this.mods.add(this.perspectiveModule = new PerspectiveModule());
        this.mods.add(this.chatEditorModule = new ChatEditorModule());
        this.mods.add(this.zoomModule = new ZoomModule());

        this.mods.add(this.fpsModule = new FPSModule());
        this.mods.add(this.coordinatesModule = new CoordinatesModule());
        this.mods.add(this.lightLevelModule = new LightLevelModule());
        this.mods.add(this.packTweaksModule = new PackTweaksModule());
        this.mods.add(this.nametagEditorModule = new NametagEditorModule());

        this.mods.add(this.autoLoginModule = new AutoLoginModule());
        this.mods.add(this.worldEditor = new WorldEditor());
        this.mods.add(this.screenshotModule = new ScreenshotModule());
        this.mods.add(new ChunkLoadingFix());
        this.mods.add(this.fofoModule = new FofoModule());

        // game fixes
        this.mods.add(new PlayerAssetFix());
        this.mods.add(new PlayerModelFix());
        this.mods.add(new SavingLevelFix());
        this.mods.add(new ChestTextFix());
        this.mods.add(new DeadEntityRenderFix());

        this.thirdPartyLoader.load();
    }

}
