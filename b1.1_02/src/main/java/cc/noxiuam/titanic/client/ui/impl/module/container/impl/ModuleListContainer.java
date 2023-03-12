package cc.noxiuam.titanic.client.ui.impl.module.container.impl;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewContainer;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import lombok.Setter;

public class ModuleListContainer extends AbstractContainer {

    private final ModulePreviewContainer previewContainer = new ModulePreviewContainer();
    @Setter private AbstractComponent currentElement = previewContainer;

    public ModuleListContainer() {
        super("/");
        this.currentElement = previewContainer;
    }

    @Override
    public void draw(float x, float y) {
        this.currentElement.position(this.x, this.y);
        this.currentElement.size(this.width, this.height);
        this.currentElement.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (this.currentElement.mouseInside(x, y)) {
            this.currentElement.mouseClicked(x, y);
        }
    }

}
