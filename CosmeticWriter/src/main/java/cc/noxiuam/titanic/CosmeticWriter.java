package cc.noxiuam.titanic;

import cc.noxiuam.titanic.data.cosmetic.ICosmetic;
import cc.noxiuam.titanic.data.cosmetic.CosmeticRegistry;
import cc.noxiuam.titanic.data.server.RegisteredServerManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private final JsonArray nameArray;

    @SneakyThrows
    public CosmeticWriter() {
        instance = this;
        this.serverManager = new RegisteredServerManager();
        this.cosmeticRegistry = new CosmeticRegistry();

        URL url = new URL("https://noxiuam.cc/titanic-client/profiles.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        this.nameArray = new JsonParser().parse(br).getAsJsonArray();
        this.start();
    }

    private void start() {
        System.out.println("New names to add: ");
        System.out.println();
        System.out.println(this.nameArray);
        System.out.println();

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

        this.nameArray.add(new JsonPrimitive(username));

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        this.start();
    }

}
