package cc.noxiuam.titanic.client.ui.component.type.module;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.impl.editor.ModSettingsEditor;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleSettingsComponent extends AbstractContainer {

    private final List<AbstractSettingComponent<?>> settingComponents = new CopyOnWriteArrayList<>();

    @Setter private AbstractModule module;

    private final RoundedTextButton backButton = new RoundedTextButton("Back");
    private final RoundedTextButton closeButton = new RoundedTextButton("Close");

    public ModuleSettingsComponent(AbstractModule module) {
        super("/modSettings");
        this.module = module;

        for (AbstractSetting<?> setting : module.settings()) {
            AbstractSettingComponent<?> component = setting.getComponent(this);
            if (component != null) {
                settingComponents.add(component);
            }
        }

    }

    @Override
    public void position(float newX, float newY) {
        super.position(newX, newY + 10);
    }

    @Override
    public void draw(float x, float y) {
        float componentHeight = 0;
        for (AbstractSettingComponent<?> component : settingComponents) {
            component.size(this.width, this.height);
            component.position(this.x, this.y + componentHeight + (component == settingComponents.get(0) ? 0 : component.getHeight()));
            component.draw(x, y);
            componentHeight += 5;
        }

        this.backButton.position(this.x, this.y + this.height);
        this.backButton.size(189 / 4F, 75 / 4F);
        this.backButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 47, this.y + this.height);
        this.closeButton.size(189 / 4F, 75 / 4F);
        this.closeButton.draw(x, y);
    }

    @Override
    public void keyTyped(char character, int key) {
        for (AbstractSettingComponent<?> component : settingComponents) {
            component.keyTyped(character, key);
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        for (AbstractSettingComponent<?> component : settingComponents) {
            if (component.mouseInside(x, y)) {
                component.mouseClicked(x, y);
            }
        }

        if (this.closeButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.mc.displayGuiScreen(null);
        } else if (this.backButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.mc.displayGuiScreen(new ModSettingsEditor());
        }
    }

}
