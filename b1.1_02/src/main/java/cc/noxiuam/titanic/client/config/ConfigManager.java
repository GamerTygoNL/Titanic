package cc.noxiuam.titanic.client.config;

import cc.noxiuam.titanic.client.util.Logger;
import lombok.Getter;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

@Getter
public class ConfigManager {

    private static final Logger LOGGER = new Logger("Config Manager");

    private final File titanicDir = new File(Minecraft.getMinecraftDir() + File.separator + "titanic");
    private final File configDir = new File(titanicDir + File.separator + "config");
    private final File thirdPartyModsDir = new File(titanicDir + File.separator + "external");
    private final File thirdPartyModsConfigDir = new File(thirdPartyModsDir + File.separator + "config");

    private final File modsConfig = new File(configDir + File.separator + "mods.json");
    private final File thirdPartyConfig = new File(thirdPartyModsDir + File.separator + "thirdParty.json");

    public ConfigManager() {
        if (isSetup()) {
            LOGGER.info("Created all required config files.");
        }
    }

    public boolean isSetup() {
        try {
            return !(!configDir.exists() && !configDir.mkdirs()
                    || !thirdPartyModsDir.exists() && !thirdPartyModsDir.mkdirs()
                    || !modsConfig.exists() && !modsConfig.createNewFile()
                    || !thirdPartyConfig.exists() && !thirdPartyConfig.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
