package cc.noxiuam.titanic.client.module.impl.normal.world;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.event.impl.network.PacketTimeUpdateEvent;

public class WorldEditor extends AbstractModule {

    private final MultiOptionSetting currentTime;

    public WorldEditor() {
        super("worldEditor", "World Editor", false);

        this.initSettings(
                this.currentTime = new MultiOptionSetting(
                        "currentTime",
                        "Current Time",
                        "Server" ,
                        "Server", "Morning", "Noon", "Evening", "Dusk", "Midnight", "Dawn"
                )
        );

        this.addEvent(PacketTimeUpdateEvent.class, this::updateTime);
    }

    private void updateTime(PacketTimeUpdateEvent event) {
        if (!this.currentTime.value().equalsIgnoreCase("Server")) {
            event.cancel();
        }

        if (this.currentTime.value().equalsIgnoreCase("Morning")) {
            this.mc.theWorld.setWorldTime(1484567587);
        } else if (this.currentTime.value().equalsIgnoreCase("Noon")) {
            this.mc.theWorld.setWorldTime(1484549927);
        } else if (this.currentTime.value().equalsIgnoreCase("Evening")) {
            this.mc.theWorld.setWorldTime(1484556647);
        } else if (this.currentTime.value().equalsIgnoreCase("Dusk")) {
            this.mc.theWorld.setWorldTime(1484557647);
        } else if (this.currentTime.value().equalsIgnoreCase("Midnight")) {
            this.mc.theWorld.setWorldTime(1484561967);
        } else if (this.currentTime.value().equalsIgnoreCase("Dawn")) {
            this.mc.theWorld.setWorldTime(1484566587);
        }
    }

}
