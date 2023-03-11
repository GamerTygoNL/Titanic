package cc.noxiuam.titanic.client.ui.impl.module.container.impl;

import cc.noxiuam.titanic.client.Titanic;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.client.ui.component.type.ScrollbarComponent;
import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewComponent;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleListContainer extends AbstractContainer {

    private final List<ModulePreviewComponent> previewComponents = new CopyOnWriteArrayList<>();

    private final ScrollbarComponent scrollbar = new ScrollbarComponent();

    public ModuleListContainer() {
        super("/");

        for (AbstractModule module : Titanic.getInstance().getModuleManager().getMods()) {
            if (module instanceof AbstractFixModule) continue;
            for (int i = 0; i < 5; i++) {
                previewComponents.add(new ModulePreviewComponent(module));
            }
        }
    }

    @Override
    public void draw(float x, float y) {

        int height = 0;
        for (ModulePreviewComponent modulePreviewComponent : this.previewComponents) {
            modulePreviewComponent.position(this.x, this.y + height);
            modulePreviewComponent.size(this.width, 16.0F);
            height += 20.0F;
        }

        float defaultPos = 112.0f;
        this.scrollbar.position(this.x + width - 6.0f, this.y + 20.0f);
        this.scrollbar.size(4.0f, height - 20.0f);
        this.scrollbar.setScrollAmount(previewComponents.size() == 0 ? defaultPos + 4.0f : 4.0f + defaultPos + (defaultPos + 8.0f) * (float)previewComponents.size());

        this.scrollbar.drawScrollable(x, y, true);
        GL11.glEnable(3089);

        RenderUtil.startScissorBox(
                (int)(this.x - 2.0f),
                (int)(this.y + 20.0f),
                (int)(this.x + this.width + 2.0f),
                (int)(this.height)
        );

        for (ModulePreviewComponent previewComponent : previewComponents) {
            previewComponent.draw(x, y);
        }

        GL11.glDisable(3089);
        this.scrollbar.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        for (ModulePreviewComponent previewComponent : previewComponents) {
            if (previewComponent.mouseInside(x, y)) {
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
