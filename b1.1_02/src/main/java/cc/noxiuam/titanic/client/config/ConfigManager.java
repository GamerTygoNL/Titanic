package cc.noxiuam.titanic.client.config;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.*;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.util.Logger;
import com.google.gson.*;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ConfigManager {

    private static final Logger LOGGER = new Logger("Config Manager");

    private final File titanicDir = new File(Minecraft.getMinecraftDir() + File.separator + "titanic");
    private final File configDir = new File(titanicDir + File.separator + "config");
    private final File thirdPartyModsDir = new File(titanicDir + File.separator + "external");
    private final File thirdPartyModsConfigDir = new File(thirdPartyModsDir + File.separator + "config");

    private final File modsConfig = new File(configDir + File.separator + "mods.json");
    private final File thirdPartyIndex = new File(thirdPartyModsDir + File.separator + "thirdParty.json");

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
                    || !thirdPartyIndex.exists() && !thirdPartyIndex.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void saveConfigs() {
        if (isSetup()) {
            writeModsProfile();
            for (AbstractModule module : Ref.getModuleManager().getMods()) {
                module.writeModuleConfig();
            }
        }
    }

    public void readConfigs() {
        if (isSetup()) {
            readModsProfile();
            // TODO: Separate mod config reading
        }
    }

    @SneakyThrows
    private void writeModsProfile() {
        List<AbstractModule> modules = new ArrayList<>(Ref.getModuleManager().getMods());

        JsonObject configObj = new JsonObject();

        for (AbstractModule module : modules) {

            JsonObject modObj = new JsonObject();
            JsonObject info = new JsonObject();
            JsonObject settings = new JsonObject();

            info.addProperty("id", module.id());
            info.addProperty("state", module.enabled());

            if (module instanceof AbstractMovableModule) {
                float x = ((AbstractMovableModule) module).x();
                float y = ((AbstractMovableModule) module).y();
                info.addProperty("x", x);
                info.addProperty("y", y);
            }

            for (AbstractSetting<?> setting : module.settings()) {
                if (setting instanceof BooleanSetting) {
                    settings.addProperty(setting.id(), ((BooleanSetting) setting).value());
                }

                if (setting instanceof StringSetting) {
                    settings.addProperty(setting.id(), ((StringSetting) setting).value());
                }

                if (setting instanceof KeybindSetting) {
                    settings.addProperty(setting.id(), ((KeybindSetting) setting).value());
                }

                if (setting instanceof MultiOptionSetting) {
                    settings.addProperty(setting.id(), ((MultiOptionSetting) setting).value());
                }

                if (setting instanceof NumberSetting) {
                    settings.addProperty(setting.id(), ((NumberSetting) setting).value());
                }
            }

            modObj.add("info", info);
            modObj.add("settings", settings);
            configObj.add(module.id(), modObj);
        }

        FileWriter modConfig = new FileWriter(this.modsConfig);
        modConfig.write(this.beautifyJson(configObj));
        modConfig.close();
    }

    @SneakyThrows
    private void readModsProfile() {
        List<AbstractModule> modules = new ArrayList<>(Ref.getModuleManager().getMods());

        JsonParser parser = new JsonParser();

        try {
            parser.parse(new FileReader(this.modsConfig)).getAsJsonObject();
        } catch (IllegalStateException ignored) {
            // config was null for whatever reason? l0l
            writeModsProfile();
            return;
        }

        JsonObject configObj = parser.parse(new FileReader(this.modsConfig)).getAsJsonObject();

        for (AbstractModule module : modules) {
            if (module instanceof AbstractFixModule) {
                module.enabled(true);
                module.addAllEvents();
                continue;
            }

            JsonObject modObject = configObj.getAsJsonObject(module.id());

            if (modObject == null) {
                if (module.enabledByDefault()) {
                    module.addAllEvents();
                }
                continue;
            }

            JsonObject info = modObject.getAsJsonObject("info");
            JsonObject settings = modObject.getAsJsonObject("settings");

            boolean state = info.get("state").getAsBoolean();
            module.enabled(state);

            if (state) {
                module.addAllEvents();
            }

            if (module instanceof AbstractMovableModule) {
                float x = info.get("x").getAsFloat();
                float y = info.get("y").getAsFloat();
                ((AbstractMovableModule) module).x(x);
                ((AbstractMovableModule) module).y(y);
            }

            for (AbstractSetting<?> setting : module.settings()) {
                if (settings.get(setting.id()) == null) {
                    continue;
                }

                if (setting instanceof BooleanSetting) {
                    boolean value = settings.get(setting.id()).getAsBoolean();
                    ((BooleanSetting) setting).value(value);
                }

                if (setting instanceof StringSetting) {
                    String value = settings.get(setting.id()).getAsString();
                    ((StringSetting) setting).value(value);
                }

                if (setting instanceof KeybindSetting) {
                    int value = settings.get(setting.id()).getAsInt();
                    ((KeybindSetting) setting).value(value);
                }

                if (setting instanceof MultiOptionSetting) {
                    String value = settings.get(setting.id()).getAsString();
                    ((MultiOptionSetting) setting).value(value);
                }

                if (setting instanceof NumberSetting) {
                    float value = settings.get(setting.id()).getAsFloat();
                    ((NumberSetting) setting).setValueFrom(value);
                }
            }
        }

    }

    public String beautifyJson(JsonObject obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

}
