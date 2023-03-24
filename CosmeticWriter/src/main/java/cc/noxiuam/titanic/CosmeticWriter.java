package cc.noxiuam.titanic;

import cc.noxiuam.titanic.data.cosmetic.ICosmetic;
import cc.noxiuam.titanic.data.cosmetic.CosmeticRegistry;
import cc.noxiuam.titanic.data.server.RegisteredServerManager;
import cc.noxiuam.titanic.util.ListUtil;
import lombok.Getter;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class CosmeticWriter {

    @Getter
    private static CosmeticWriter instance;

    private final RegisteredServerManager serverManager;
    private final CosmeticRegistry cosmeticRegistry;

    private final List<String> nameCache = new CopyOnWriteArrayList<>();

    public CosmeticWriter() {
        instance = this;
        this.serverManager = new RegisteredServerManager();
        this.cosmeticRegistry = new CosmeticRegistry();
        this.start();
    }

    private void start() {
        StringBuilder sb = new StringBuilder("New names to add: ");

        for (String name : this.nameCache) {
            sb.append("\"").append(name).append("\"").append(ListUtil.isLast(this.nameCache, name) ? "" : ", ");
        }

        System.out.println(sb);

        System.out.println("Enter a username to grant.");
        String username = new Scanner(System.in).nextLine();

        this.pickCosmetic(username);
    }

    private void pickCosmetic(String username) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(this.cosmeticRegistry.getCosmeticList());
        System.out.println("Choose a cosmetic, the name is not case sensitive.");

        String cosmeticName = scanner.nextLine();

        ICosmetic cosmetic = this.cosmeticRegistry.getCosmeticByName(cosmeticName);

        if (cosmetic == null) {
            System.out.println("No cosmetic exists with that name, please try again.");
            this.pickCosmetic(username);
            return;
        }

        new ProfileWriter(username, cosmetic).writeProfile();
        this.nameCache.add(username);

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        this.start();
    }

}
