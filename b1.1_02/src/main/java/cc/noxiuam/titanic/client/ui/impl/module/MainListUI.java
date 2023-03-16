package cc.noxiuam.titanic.client.ui.impl.module;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.impl.module.container.impl.ModuleListContainer;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;

public class MainListUI extends AbstractComponent {

    private final ModuleListContainer moduleListContainer = new ModuleListContainer();

    @Override
    public void size(float width, float height) {
        super.size(width, height);
        moduleListContainer.size(width - 20, height - 50);
    }

    @Override
    public void position(float newX, float newY) {
        super.position(newX, newY);
        moduleListContainer.position(newX + 10, newY + 10);
    }

    @Override
    public void draw(float x, float y) {
        RenderUtil.drawRoundedRect(
                this.x,
                this.y + 1.0f,
                this.x + this.width,
                this.y + this.height - 1.0f,
                5.0f,
                0x80000000
        );

        moduleListContainer.draw(x, y);
    }

    @Override
    public void keyTyped(char character, int key) {
        this.moduleListContainer.keyTyped(character, key);
    }

    @Override
    public void mouseClicked(float x, float y) {
        super.mouseClicked(x, y);
        this.moduleListContainer.mouseClicked(x, y);
    }

}
