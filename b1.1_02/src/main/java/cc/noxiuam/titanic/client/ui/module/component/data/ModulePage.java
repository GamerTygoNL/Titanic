package cc.noxiuam.titanic.client.ui.module.component.data;

import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewComponent;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ModulePage {

    public final List<ModulePreviewComponent> previewComponents = new CopyOnWriteArrayList<>();

}
