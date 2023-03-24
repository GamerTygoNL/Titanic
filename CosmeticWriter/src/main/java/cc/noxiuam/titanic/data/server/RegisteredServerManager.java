package cc.noxiuam.titanic.data.server;

import cc.noxiuam.titanic.data.server.impl.AlphaPlace;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class RegisteredServerManager {

    private final List<RegisteredServer> registeredServers = new CopyOnWriteArrayList<>();

    private final AlphaPlace alphaPlace;

    public RegisteredServerManager() {

        this.registeredServers.add(this.alphaPlace = new AlphaPlace());
    }

}
