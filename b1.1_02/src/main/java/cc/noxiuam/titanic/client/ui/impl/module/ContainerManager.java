package cc.noxiuam.titanic.client.ui.impl.module;

import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerManager {

    public List<AbstractContainer> containers = new CopyOnWriteArrayList<>();

    public ContainerManager() {

    }

}
