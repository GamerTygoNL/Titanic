package cc.noxiuam.titanic;

import cc.noxiuam.titanic.data.cosmetic.ICosmetic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;

@AllArgsConstructor
public class ProfileWriter {

    private final String username;
    private final ICosmetic cosmetic;

    @SneakyThrows
    public void writeProfile() {

        File profileFile = new File("CosmeticWriter/src/main/resources/" + username);

        JsonObject profileObj = new JsonObject();
        JsonObject cosmeticObj = new JsonObject();

        cosmeticObj.addProperty("name", cosmetic.getName());
        cosmeticObj.addProperty("description", cosmetic.getDescription());
        cosmeticObj.addProperty("equipped", cosmetic.isEquippedByDefault());
        cosmeticObj.addProperty("type", cosmetic.getType());
        cosmeticObj.addProperty("location", cosmetic.getLocation());

        profileObj.add("cosmetic", cosmeticObj);
        profileObj.addProperty("rank", "default");

        FileWriter fileWriter = new FileWriter(profileFile);
        fileWriter.write(this.beautifyJson(profileObj));
        fileWriter.close();
    }

    private String beautifyJson(JsonObject obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

}
