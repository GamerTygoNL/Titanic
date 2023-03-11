package cc.noxiuam.titanic.client.ui.component.type.module;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModulePreviewComponent extends AbstractComponent {

    private final RoundedIconButton settingsButton = new RoundedIconButton(
            "/titanic/pencil.png",
            false,
            8,
            8,
            4.5F,
            4.5F
    );

    private final ColorFade backgroundColor = new ColorFade(
            0x80000000,
            0xBF000000
    );

    private final AbstractModule module;

    @Override
    public void draw(float x, float y) {

        settingsButton.size(15, 15);
        settingsButton.position(this.x + width - 16, this.y);

        RenderUtil.drawRoundedRect(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                backgroundColor.getColor(mouseInside(x, y)).getRGB()
        );

        settingsButton.draw(x, y);

        this.mc.fontRenderer.drawStringWithShadow(module.name(), (int) (this.x + 5), (int) this.y + 4, module.enabled() ? -1 : 0xFFADADAD);
    }

}
