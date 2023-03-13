package cc.noxiuam.titanic.client.ui.impl.module.container.impl;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.component.type.footer.FooterContainer;
import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewContainer;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import lombok.Getter;
import lombok.Setter;

public class ModuleListContainer extends AbstractContainer {

    private final FooterContainer footer = new FooterContainer(this);

    @Getter private final ModulePreviewContainer previewContainer = new ModulePreviewContainer();
    @Setter private AbstractComponent currentComponent;

    public ModuleListContainer() {
        super("/");
        this.currentComponent = previewContainer;
    }

    @Override
    public void draw(float x, float y) {
        this.currentComponent.position(this.x, this.y);
        this.currentComponent.size(this.width, this.height);
        this.currentComponent.draw(x, y);

        this.footer.position(this.x, this.y + 10);
        this.footer.size(this.width, this.height);
        this.footer.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        this.footer.mouseClicked(x, y);
        this.currentComponent.mouseClicked(x, y);
    }

}
