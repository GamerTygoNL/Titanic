package cc.noxiuam.titanic.client.ui.component.type.module;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.impl.editor.ModSettingsEditor;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.ui.impl.module.container.impl.ModuleListContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleSettingsComponent extends AbstractContainer {

    private final List<AbstractSettingComponent<?>> settingComponents = new CopyOnWriteArrayList<>();

    @Setter private AbstractModule module;
    private final ModuleListContainer container;
    private final AbstractContainer parent;

    private final RoundedIconButton backButton = new RoundedIconButton(
            "/titanic/back.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );
    private final RoundedIconButton closeButton = new RoundedIconButton(
            "/titanic/close.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );

    public ModuleSettingsComponent(ModuleListContainer container, ModulePreviewContainer parent, AbstractModule module) {
        super("/modSettings");
        this.module = module;
        this.container = container;
        this.parent = parent;

        for (AbstractSetting<?> setting : module.settings()) {
            AbstractSettingComponent<?> component = setting.getComponent(this);
            if (component != null) {
                settingComponents.add(component);
            }
        }

    }

    @Override
    public void handleUpdate() {
        for (AbstractSettingComponent<?> component : settingComponents) {
            component.handleUpdate();
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
            component.position(this.x, this.y + componentHeight);
            component.draw(x, y);
            componentHeight += (component.getSetting() instanceof StringSetting) ? component.getHeight() + 10 : component.getHeight() + 5;
        }

        this.backButton.position(this.x, this.y + this.height);
        this.backButton.size(20, 20);
        this.backButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 20, this.y + this.height);
        this.closeButton.size(20, 20);
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
            this.container.setCurrentComponent(this.parent);
        }
    }

}
