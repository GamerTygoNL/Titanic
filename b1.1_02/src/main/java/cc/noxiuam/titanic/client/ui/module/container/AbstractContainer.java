package cc.noxiuam.titanic.client.ui.module.container;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractContainer extends AbstractComponent {

    private final String path;

}
