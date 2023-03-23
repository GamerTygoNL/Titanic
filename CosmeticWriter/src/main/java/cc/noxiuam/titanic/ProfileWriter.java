package cc.noxiuam.titanic;

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
    private final String name;
    private final String desc;
    private final String location;

    @SneakyThrows
    public void writeProfile() {

        File profileFile = new File("CosmeticWriter/src/main/resources/" + username);

        JsonObject profileObj = new JsonObject();
        JsonObject cosmeticObj = new JsonObject();

        cosmeticObj.addProperty("name", this.name);
        cosmeticObj.addProperty("description", this.desc);
        cosmeticObj.addProperty("equipped", true);
        cosmeticObj.addProperty("type", "cape");
        cosmeticObj.addProperty("location", this.location);

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
