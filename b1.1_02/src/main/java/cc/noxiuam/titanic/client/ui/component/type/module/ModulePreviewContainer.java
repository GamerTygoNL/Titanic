package cc.noxiuam.titanic.client.ui.component.type.module;

import cc.noxiuam.titanic.Titanic;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.client.ui.component.type.module.impl.ModulePreviewComponent;
import cc.noxiuam.titanic.client.ui.component.type.module.data.ModulePage;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModulePreviewContainer extends AbstractContainer {

    @Getter private final List<ModulePage> modulePages = new CopyOnWriteArrayList<>();

    private final List<AbstractModule> queuedModules = new CopyOnWriteArrayList<>();

    public int pageNumber = 0;

    public ModulePreviewContainer() {
        super("/mods");
        List<AbstractModule> mods = Titanic.getInstance().getModuleManager().getMods();

        mods.removeIf(mod -> mod instanceof AbstractFixModule);

        for (float i = 0; i < mods.size() / 5F; i++) {
            modulePages.add(new ModulePage());
        }

        for (AbstractModule module : mods) {
            if (module instanceof AbstractFixModule) continue;

            for (ModulePage modulePage : this.modulePages) {
                if (queuedModules.contains(module)) {
                    continue;
                }

                if (modulePage.getPreviewComponents().size() >= 5) {
                    continue;
                }

                modulePage.getPreviewComponents().add(new ModulePreviewComponent(this, module));
                queuedModules.add(module);
            }
        }
    }

    @Override
    public void draw(float x, float y) {
        int height = 0;
        for (ModulePreviewComponent modulePreviewComponent : this.modulePages.get(pageNumber).getPreviewComponents()) {
            modulePreviewComponent.position(this.x, this.y + height);
            modulePreviewComponent.size(this.width, 16.0F);
            height += 20.0F;
        }

        for (ModulePreviewComponent previewComponent : this.modulePages.get(pageNumber).getPreviewComponents()) {
            previewComponent.draw(x, y);
        }
    }

    @Override
    public void mouseClicked(float x, float y) {

        for (ModulePreviewComponent previewComponent : this.modulePages.get(pageNumber).getPreviewComponents()) {
            if (previewComponent.mouseInside((int) x, (int) y)) {
                if (previewComponent.getSettingsButton().mouseInside(x, y)) {
                    // TODO: Settings menu
                    return;
                }
                SoundUtil.playClick();
                previewComponent.getModule().toggle();
            }
        }
    }

}
