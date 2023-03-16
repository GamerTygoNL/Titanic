package cc.noxiuam.titanic.client.ui.component.type.module.impl;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.component.type.module.ModulePreviewContainer;
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

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);
    private final ColorFade enabledOutline = new ColorFade(0x00000000, 0xFF00C2FF);

    private final ModulePreviewContainer container;
    private final AbstractModule module;

    @Override
    public void draw(float x, float y) {
        settingsButton.size(15, 15);
        settingsButton.position(this.x + width - 16, this.y);

        RenderUtil.drawRoundedOutline(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                3.0F,
                module.enabled()
                        ? enabledOutline.getColor(true).getRGB()
                        : outlineColor.getColor(mouseInside((int) x, (int) y) && this.container.mouseInside(x, y)).getRGB()
        );

        RenderUtil.drawRoundedRect(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                backgroundColor.getColor(mouseInside((int) x, (int) y) && this.container.mouseInside(x, y)).getRGB()
        );

        if (module.settings().size() > 0) {
            settingsButton.draw(x, y);
        }

        this.mc.fontRenderer.drawStringWithShadow(module.name(), (int) (this.x + 5), (int) this.y + 4, module.enabled() ? -1 : 0xFFADADAD);
    }

}
