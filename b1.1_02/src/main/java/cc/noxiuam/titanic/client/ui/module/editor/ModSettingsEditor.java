package cc.noxiuam.titanic.client.ui.module.editor;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.ui.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.module.MainListUI;
import org.lwjgl.input.Keyboard;

public class ModSettingsEditor extends GuiScreenWrapper {

    private final MainListUI moduleList = new MainListUI();

    @Override
    public void initGui() {
        super.initGui();

        float width = 447.0F / 2;
        float height = 300.0F / 2;
        this.moduleList.size(width, height);
        this.moduleList.position(this.width / 2.0f - width / 2.0f, this.height / 2.0f - height / 2.0f);
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
        Ref.getConfigManager().saveConfigs();
    }

    @Override
    public void updateScreen() {
        this.moduleList.handleUpdate();
    }

    @Override
    public void drawScreen(int x, int y) {
        this.moduleList.draw(x, y);
    }

    @Override
    protected void keyTyped(char c, int i) {
        super.keyTyped(c, i);
        this.moduleList.keyTyped(c, i);
    }

    @Override
    public void onMouseClick(int x, int y, int button) {
        super.onMouseClick(x, y, button);
        this.moduleList.mouseClicked(x, y);
    }

}
