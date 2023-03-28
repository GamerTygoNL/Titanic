package cc.noxiuam.titanic.client.ui.module.component;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.external.ThirdPartyModule;
import cc.noxiuam.titanic.client.ui.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.ui.module.container.impl.ModuleListContainer;
import cc.noxiuam.titanic.client.ui.module.container.impl.SettingsFooterContainer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThirdPartyModsComponent extends AbstractContainer {

    private final ModuleListContainer container;
    private final SettingsFooterContainer settingsFooter;

    private final List<ThirdPartyModPreviewComponent> modulePreviewContainers = new CopyOnWriteArrayList<>();

    public ThirdPartyModsComponent(ModuleListContainer container) {
        super("/mods/thirdparty");
        this.container = container;
        this.settingsFooter = new SettingsFooterContainer(this.container);

        for (ThirdPartyModule module : Ref.getModuleManager().getThirdPartyLoader().getThirdPartyModules()) {
            this.modulePreviewContainers.add(
                    new ThirdPartyModPreviewComponent(module)
            );
        }
    }

    @Override
    public void draw(float x, float y) {
        this.settingsFooter.position(this.container.getX(), this.container.getY() + 10);
        this.settingsFooter.size(this.container.getWidth(), this.container.getHeight());
        this.settingsFooter.draw(x, y);

        int height = 0;
        for (ThirdPartyModPreviewComponent component : this.modulePreviewContainers) {
            component.position(this.x, this.y + height);
            component.size(this.width, 16.0F);
            height += 20.0F;
        }

        for (ThirdPartyModPreviewComponent component : this.modulePreviewContainers) {
            component.draw(x, y);
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        for (ThirdPartyModPreviewComponent component : this.modulePreviewContainers) {
            if (component.mouseInside((int) x, (int) y)) {
                component.mouseClicked(x, y);
                return;
            }
        }

        this.settingsFooter.mouseClicked(x, y);
    }

}
