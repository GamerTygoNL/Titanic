package cc.noxiuam.titanic.client.ui.impl.editor.component;

import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HUDModuleComponent extends AbstractComponent {

    private static final int OUTLINE_COLOR = 0xFF00C2FF;
    private static final int HOVER_COLOR = 0x7000C2FF;

    private final AbstractMovableModule module;

    @Override
    public void draw(float x, float y) {
         this.position(this.module.x() - 1, this.module.y() - 1);
        this.size(this.module.width(), this.module.height());

        RenderUtil.drawRectWithOutline(
                0.0F,
                0.0F,
                this.module.width(),
                this.module.height(),
                0.49f,
                OUTLINE_COLOR,
                HOVER_COLOR
        );
    }

}
