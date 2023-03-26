package cc.noxiuam.titanic.event.impl.gui;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.src.GuiMainMenu;

@Getter
@AllArgsConstructor
public class MainMenuLogoDrawEvent extends AbstractEvent {

    private GuiMainMenu mainMenu;

}
