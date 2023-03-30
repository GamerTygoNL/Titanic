package cc.noxiuam.titanic.client.module.external;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.Titanic;
import cc.noxiuam.titanic.client.ui.module.external.ThirdPartyOpenMenu;
import cc.noxiuam.titanic.client.util.Logger;
import cc.noxiuam.titanic.client.util.chat.ChatUtil;
import cc.noxiuam.titanic.client.util.zip.ZipUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class ThirdPartyLoader {

    private static final Logger LOGGER = new Logger("Third Party Mods");

    private final List<ThirdPartyModule> thirdPartyModules = new CopyOnWriteArrayList<>();
    private int totalModsDownloaded = 0;

    @Setter private boolean locked;

    @SneakyThrows
    public ThirdPartyLoader() {
        File thirdPartyIndex = Ref.getConfigManager().getThirdPartyIndex();
        JsonParser parser = new JsonParser();

        try {
            parser.parse(new FileReader(thirdPartyIndex)).getAsJsonObject();
        } catch (IllegalStateException ignored) {
            return;
        }

        JsonObject indexObject = parser.parse(new FileReader(thirdPartyIndex)).getAsJsonObject();

        this.locked = indexObject.get("locked") != null && indexObject.get("locked").getAsBoolean();
    }

    public void load() {
        new Thread(() -> {
            this.writeLatestModInfo();
            this.loadModsFromIndex();
        }).start();
    }

    /**
     * Writes the latest third party mod info locally to the config.
     */
    @SneakyThrows
    public void writeLatestModInfo() {
        URL url = new URL("https://noxiuam.cc/titanic-client/api/mods/thirdparty/mod-index.json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JsonObject remoteIndex = new JsonParser().parse(br).getAsJsonObject();
        remoteIndex.addProperty("locked", this.locked);

        FileWriter thirdParty = new FileWriter(Ref.getConfigManager().getThirdPartyIndex());
        thirdParty.write(Ref.getConfigManager().beautifyJson(remoteIndex));
        thirdParty.close();
    }

    /**
     * Loads the latest mod data from Titanic client's servers.
     */
    @SneakyThrows
    private void loadModsFromIndex() {
        File thirdPartyIndex = Ref.getConfigManager().getThirdPartyIndex();
        JsonParser parser = new JsonParser();

        try {
            parser.parse(new FileReader(thirdPartyIndex)).getAsJsonObject();
        } catch (IllegalStateException ignored) {
            return;
        }

        JsonObject indexObject = parser.parse(new FileReader(thirdPartyIndex)).getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : indexObject.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("locked")) {
                continue;
            }

            JsonObject modObject = entry.getValue().getAsJsonObject();

            String name = modObject.get("name").getAsString();
            String mainClass = modObject.get("mainClass").getAsString();
            boolean extractable = modObject.get("extractable").getAsBoolean();
            boolean executable = modObject.get("executable").getAsBoolean();
            String modPath = modObject.get("modPath").getAsString();
            String modFile = modObject.get("modFile").getAsString();
            String executeCommand = modObject.get("executeCommand").getAsString();
            String link = modObject.get("link").getAsString();

            this.thirdPartyModules.add(new ThirdPartyModule(
                    name,
                    mainClass,
                    extractable,
                    executable,
                    link,
                    Ref.getConfigManager().getThirdPartyModsDir() + File.separator + modPath,
                    modFile,
                    executeCommand
            ));

            if (new File(modPath).exists()) {
                this.totalModsDownloaded++;
            }

            LOGGER.debug("Loaded Third Party from index: " + name);
        }

        LOGGER.debug("Loaded " + this.thirdPartyModules.size() + " Third Party mods.");
        LOGGER.debug("User has " + this.totalModsDownloaded + " total downloaded.");

        if (Titanic.getInstance().isDebug()) {
            this.downloadAndSetupMod(this.thirdPartyModules.get(0)); // for testing purposes
        }
    }

    /**
     * Downloads the mod, and extracts or loads it depending on what is defined in the remote mod data.
     * <p>
     * @param module - The module you wish to load.
     */
    @SneakyThrows
    public void downloadAndSetupMod(ThirdPartyModule module) {
        String zipName = Ref.getConfigManager().getThirdPartyModsDir()
                + File.separator
                + module.getName().replaceAll(" ", "") + ".zip";

        if (new File(zipName).exists()) {
            LOGGER.debug("Mod is already installed, ignoring.");
            if (module.isExtractable()) {
                this.extractMod(module, zipName);
            } else {
                this.loadModIntoGame(module);
            }
            return;
        }

        try {
            LOGGER.debug("Starting download of mod: " + module.getName());
            ChatUtil.addChatMessage("Loading mod: " + module.getName());
            URL url = new URL(module.getLink());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "Mozilla/4.0");

            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fis = new FileOutputStream(zipName);

            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }

            fis.close();
            bis.close();
            ChatUtil.addChatMessage("Downloaded " + module.getName() + " successfully!");
            LOGGER.debug("Downloaded \"" + module.getName() + "\" successfully!");
        } catch (Exception e) {
            LOGGER.error("Could not download mod \"" + module.getName() + "\" (" + e.getMessage() + ")");
            return;
        }

        if (module.isExtractable()) {
            this.extractMod(module, zipName);
        } else {
            this.loadModIntoGame(module);
        }
    }

    /**
     * Remaps and loads a jar into the game.
     * <p>
     * @param module - The module in question.
     */
    private void loadModIntoGame(ThirdPartyModule module) {
        // TODO: make the comment a thing.
    }

    /**
     * Extracts the mod's zip file if it has one.
     * <p>
     * @param module - The module being extracted.
     * @param zipPath - The full path to the mod's zip archive.
     */
    private void extractMod(ThirdPartyModule module, String zipPath) {
        String extractionPoint = Ref.getConfigManager().getThirdPartyModsDir().toString();

        if (!new File(module.getModPath()).exists()) {
            LOGGER.debug("Extracting " + module.getName());
            ChatUtil.addChatMessage("Extracting " + module.getName());
            try {
                ZipUtil.unzip(zipPath, extractionPoint);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (module.isExecutable()) {
            this.executeMod(module);
        }
    }

    /**
     * Executes the mod file on a separate process.
     * <p>
     * @param module - The module being executed.
     */
    @SneakyThrows
    private void executeMod(ThirdPartyModule module) {
        ChatUtil.addChatMessage("Starting " + module.getName() + "...");

        if (this.locked) {
            Ref.getMinecraft().displayGuiScreen(new ThirdPartyOpenMenu());
        }

        Runtime.getRuntime().exec(
                module.getExecuteCommand()
                        + module.getModPath()
                        + File.separator
                        + module.getModFile()
        );
    }

}
