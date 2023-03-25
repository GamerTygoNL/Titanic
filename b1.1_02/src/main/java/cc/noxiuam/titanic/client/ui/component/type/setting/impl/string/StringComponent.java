package cc.noxiuam.titanic.client.ui.component.type.setting.impl.string;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;

public class StringComponent extends AbstractSettingComponent<String> {

    private final TextBoxComponent textBoxComponent;

    private final String[] notes = new String[] {
            "Note: To save the text, press enter",
            "or reselect the box."
    };

    public StringComponent(AbstractSetting<String> setting, ModuleSettingsComponent list) {
        super(setting, list);
        this.textBoxComponent = new TextBoxComponent((StringSetting) this.setting);
    }

    @Override
    public void handleUpdate() {
        this.textBoxComponent.handleUpdate();
    }

    @Override
    public void position(float newX, float newY) {
        super.position(newX, newY + 3);
    }

    @Override
    public void draw(float x, float y) {

        if (this.textBoxComponent.using) {

            RenderUtil.drawRoundedRect(
                    this.x - 2,
                    this.y - 3,
                    this.x + this.width + 3,
                    this.y + this.height + 2,
                    5,
                    0x70787878
            );

            float noteY = 0;
            for (String str : this.notes) {
                this.mc.fontRenderer.drawStringWithShadow(
                        str,
                        (int) (this.x + 2.0F),
                        (int) (this.y + 30 + noteY),
                        -1
                );
                noteY += 10;
            }

        }

        this.textBoxComponent.size(this.width, 12);
        this.textBoxComponent.position(this.x + 1.0F, this.y + 13);
        this.textBoxComponent.draw(x, y);

        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );
    }

    @Override
    public float getHeight() {
        return this.textBoxComponent.using ? 50 : 25;
    }

    @Override
    public void mouseClicked(float x, float y) {
        this.textBoxComponent.mouseClicked(x, y);
    }

    @Override
    public void keyTyped(char character, int key) {
        this.textBoxComponent.keyTyped(character, key);
    }

}
