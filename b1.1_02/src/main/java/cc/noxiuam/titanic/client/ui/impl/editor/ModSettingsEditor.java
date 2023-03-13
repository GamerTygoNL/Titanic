package cc.noxiuam.titanic.client.ui.impl.editor;

import cc.noxiuam.titanic.client.ui.impl.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.impl.module.MainListUI;

public class ModSettingsEditor extends GuiScreenWrapper {

    private final MainListUI moduleList = new MainListUI();

    @Override
    public void initGui() {
        super.initGui();

        float width = 447.0F / 2;
        float height = 300.0F / 2;
        this.moduleList.size(width, height);
        this.moduleList.position(this.width / 2.0f - width / 2.0f, this.height / 2.0f - height / 2.0f);
    }

    @Override
    public void drawScreen(int x, int y) {
        this.moduleList.draw(x, y);
    }

    @Override
    public void onMouseClick(int x, int y, int button) {
        super.onMouseClick(x, y, button);
        this.moduleList.mouseClicked(x, y);
    }

}
