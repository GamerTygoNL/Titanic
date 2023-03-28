package cc.noxiuam.titanic.client.ui.component.type.setting.impl.keybind;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;

public class KeybindComponent extends AbstractSettingComponent<Integer> {

    private final KeybindButton button;

    public KeybindComponent(AbstractSetting<Integer> setting, ModuleSettingsComponent list) {
        super(setting, list);
        this.button = new KeybindButton((KeybindSetting) setting);
    }

    @Override
    public void draw(float x, float y) {

        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );

        this.button.size(50, 12);
        this.button.position(this.x + this.width - 48, this.y);
        this.button.draw(x, y);
    }

    @Override
    public float getHeight() {
        return 10;
    }

    @Override
    public void keyTyped(char character, int key) {
        this.button.keyTyped(character, key);
    }

    @Override
    public void mouseClicked(float x, float y) {
        this.button.mouseClicked(x, y);
    }

}
