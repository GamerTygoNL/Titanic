package cc.noxiuam.titanic.client.ui.module.component;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.external.ThirdPartyModule;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO:
 * - An indicator if you have the mod downloaded or not.
 * - Make the preview a dropdown when clicked, with the option to delete, or load/execute the mod.
 */
@Getter
@AllArgsConstructor
public class ThirdPartyModPreviewComponent extends AbstractComponent {

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);
    private final ColorFade textColor = new ColorFade(0xFFADADAD, -1);

    private final ThirdPartyModule module;

    @Override
    public void draw(float x, float y) {
        RenderUtil.drawRoundedOutline(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                3.0F,
                this.outlineColor.getColor(this.mouseInside((int) x, (int) y)).getRGB()
        );

        RenderUtil.drawRoundedRect(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                this.backgroundColor.getColor(this.mouseInside((int) x, (int) y)).getRGB()
        );

        this.mc.fontRenderer.drawStringWithShadow(
                this.module.getName(),
                (int) this.x + 5,
                (int) this.y + 4,
                this.textColor.getColor(this.mouseInside((int) x, (int) y)).getRGB()
        );
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (this.mouseInside(x, y)) {
            SoundUtil.playClick();
            new Thread(() -> Ref.getModuleManager().getThirdPartyLoader().downloadAndSetupMod(this.module)).start();
        }
    }

}
