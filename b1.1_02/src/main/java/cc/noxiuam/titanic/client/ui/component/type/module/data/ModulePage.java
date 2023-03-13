package cc.noxiuam.titanic.client.ui.component.type.module.data;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.ui.component.type.module.impl.ModulePreviewComponent;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModulePage {

    public final List<ModulePreviewComponent> previewComponents = new CopyOnWriteArrayList<>();

    public boolean containsModule(AbstractModule module) {
        for (ModulePreviewComponent previewComponent : previewComponents) {
            if (module.name().equalsIgnoreCase(previewComponent.getModule().name())) {
                return true;
            }
        }

        return false;
    }

}
