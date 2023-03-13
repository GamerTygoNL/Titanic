package cc.noxiuam.titanic.client.ui.component.type.footer;

import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.impl.editor.HudLayoutEditor;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.ui.impl.module.container.impl.ModuleListContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;

public class FooterContainer extends AbstractContainer {

    private final RoundedIconButton editHudButton = new RoundedIconButton(
            "/titanic/pencil.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );

    private final RoundedTextButton closeButton = new RoundedTextButton("Close");
    private final RoundedTextButton leftButton = new RoundedTextButton("<");
    private final RoundedTextButton rightButton = new RoundedTextButton(">");

    private final ModuleListContainer parent;

    public FooterContainer(ModuleListContainer parent) {
        super("/footer");
        this.parent = parent;
    }

    @Override
    public void draw(float x, float y) {
        this.editHudButton.position(this.x, this.y + this.height);
        this.editHudButton.size(20, 20);
        this.editHudButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 47, this.y + this.height);
        this.closeButton.size(189 / 4F, 75 / 4F);
        this.closeButton.draw(x, y);

        boolean showPageButtons = this.parent.getPreviewContainer().getModulePages().size() > 1;

        if (showPageButtons) {
            this.leftButton.position(this.x + this.width / 2.165F - 12, this.y + this.height + 2.5F);
            this.leftButton.size(15, 15);
            this.leftButton.draw(x, y);

            this.rightButton.position(this.x + this.width / 2.165F + 12, this.y + this.height + 2.5F);
            this.rightButton.size(15, 15);
            this.rightButton.draw(x, y);
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        boolean showPageButtons = this.parent.getPreviewContainer().getModulePages().size() > 1;

        if (this.closeButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(null);
        } else if (this.editHudButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(new HudLayoutEditor());
        } else if (showPageButtons && this.leftButton.mouseInside(x, y) && this.parent.getPreviewContainer().pageNumber > 0) {
            SoundUtil.playClick();
            this.parent.getPreviewContainer().pageNumber--;
        } else if (showPageButtons && this.rightButton.mouseInside(x, y)
                && this.parent.getPreviewContainer().pageNumber + 1 < this.parent.getPreviewContainer().getModulePages().size()) {
            SoundUtil.playClick();
            this.parent.getPreviewContainer().pageNumber++;
        }
    }

}
