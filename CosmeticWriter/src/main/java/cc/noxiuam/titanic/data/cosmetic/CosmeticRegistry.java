package cc.noxiuam.titanic.data.cosmetic;

import cc.noxiuam.titanic.CosmeticWriter;
import cc.noxiuam.titanic.data.cosmetic.impl.AvolitionCape;
import cc.noxiuam.titanic.data.cosmetic.impl.FriendCosmetic;
import cc.noxiuam.titanic.data.cosmetic.impl.NaibuuCape;
import cc.noxiuam.titanic.data.server.RegisteredServer;
import cc.noxiuam.titanic.exception.NoCosmeticFoundException;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class CosmeticRegistry {

    private final List<Cosmetic> registeredCosmetics = new CopyOnWriteArrayList<>();

    public CosmeticRegistry() {
        this.registeredCosmetics.add(new AvolitionCape());
        this.registeredCosmetics.add(new FriendCosmetic());
        this.registeredCosmetics.add(new NaibuuCape());

        for (RegisteredServer server : CosmeticWriter.getInstance().getServerManager().getRegisteredServers()) {
            if (server.getCosmetics() == null) continue;
            this.registeredCosmetics.addAll(server.getCosmetics());
        }
    }

    public String getCosmeticList() {
        StringBuilder sb = new StringBuilder("Available Cosmetics (" + this.registeredCosmetics.size() + "): ");

        for (Cosmetic cosmetic : this.registeredCosmetics) {
            boolean isLast = this.registeredCosmetics.indexOf(cosmetic) == this.registeredCosmetics.size() - 1;
            sb.append(cosmetic.getName()).append(isLast ? "." : ", ");
        }

        return sb.toString();
    }

    public Cosmetic getCosmeticByName(String targetName) {

        for (Cosmetic cosmetic : this.registeredCosmetics) {
            if (cosmetic.getName().equalsIgnoreCase(targetName)) {
                return cosmetic;
            }
        }

        return null;
    }

}
