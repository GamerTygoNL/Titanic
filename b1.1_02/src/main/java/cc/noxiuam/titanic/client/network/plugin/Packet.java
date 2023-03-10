package cc.noxiuam.titanic.client.network.plugin;

import java.util.function.Consumer;

public interface Packet {

    int getId();

    Consumer<?> handle();

}
