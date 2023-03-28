package cc.noxiuam.titanic.client.ui.module.container.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewComponent;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.module.component.data.ModulePage;
import cc.noxiuam.titanic.client.ui.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModulePreviewContainer extends AbstractContainer {

    @Getter private final ModuleListContainer container;

    @Getter private final List<ModulePage> modulePages = new CopyOnWriteArrayList<>();
    @Getter private final List<AbstractModule> queuedModules = new CopyOnWriteArrayList<>();

    public int pageNumber = 0;

    private final RoundedTextButton leftButton = new RoundedTextButton("<");
    private final RoundedTextButton rightButton = new RoundedTextButton(">");

    public ModulePreviewContainer(ModuleListContainer container) {
        super("/mods");
        this.container = container;

        List<AbstractModule> mods = Ref.getModuleManager().getMods();
        mods.removeIf(mod -> mod instanceof AbstractFixModule);

        for (float i = 0; i < mods.size() / 5F; i++) {
            this.modulePages.add(new ModulePage());
        }

        for (AbstractModule module : mods) {
            for (ModulePage modulePage : this.modulePages) {
                if (module instanceof AbstractFixModule) continue;
                if (this.queuedModules.contains(module)) {
                    continue;
                }

                if (modulePage.getPreviewComponents().size() >= 5) {
                    continue;
                }

                modulePage.getPreviewComponents().add(new ModulePreviewComponent(this, module));
                this.queuedModules.add(module);
            }
        }
    }

    @Override
    public void draw(float x, float y) {
        int height = 0;
        for (ModulePreviewComponent modulePreviewComponent : this.modulePages.get(this.pageNumber).getPreviewComponents()) {
            modulePreviewComponent.position(this.x, this.y + height);
            modulePreviewComponent.size(this.width, 16.0F);
            height += 20.0F;
        }

        for (ModulePreviewComponent previewComponent : this.modulePages.get(this.pageNumber).getPreviewComponents()) {
            previewComponent.draw(x, y);
        }

        boolean showPageButtons = this.modulePages.size() > 1;

        if (showPageButtons) {
            this.leftButton.setDisabled(this.pageNumber == 0);
            this.leftButton.position(this.x + this.width / 2.165F - 12, this.y + this.height + 12);
            this.leftButton.size(15, 15);
            this.leftButton.draw(x, y);

            this.rightButton.setDisabled(this.pageNumber == this.modulePages.size() - 1);
            this.rightButton.position(this.x + this.width / 2.165F + 12, this.y + this.height + 12);
            this.rightButton.size(15, 15);
            this.rightButton.draw(x, y);
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        boolean showPageButtons = this.modulePages.size() > 1;

        for (ModulePreviewComponent previewComponent : this.modulePages.get(this.pageNumber).getPreviewComponents()) {
            if (previewComponent.mouseInside((int) x, (int) y)) {
                if (previewComponent.getSettingsButton().mouseInside(x, y)
                        && previewComponent.getModule().settings().size() > 0) {
                    SoundUtil.playClick();
                    this.container.setCurrentComponent(
                            new ModuleSettingsComponent(this.container, this, previewComponent.getModule())
                    );
                    return;
                }

                SoundUtil.playClick();
                previewComponent.getModule().toggle();
            }
        }

        if (showPageButtons && this.leftButton.mouseInside(x, y) && this.pageNumber > 0) {
            SoundUtil.playClick();
            this.pageNumber--;
        } else if (showPageButtons && this.rightButton.mouseInside(x, y) && this.pageNumber + 1 < this.modulePages.size()) {
            SoundUtil.playClick();
            this.pageNumber++;
        }
    }

}
