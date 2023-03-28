package cc.noxiuam.titanic.client.ui.component.type.setting;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractSettingComponent<T> extends AbstractComponent {

    protected final AbstractSetting<T> setting;
    protected final ModuleSettingsComponent list;

    @Override
    public abstract float getHeight();

    @Override
    public void size(float width, float height) {
        super.size(width, this.getHeight());
    }

}
