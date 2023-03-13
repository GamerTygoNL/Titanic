package cc.noxiuam.titanic.client.util.sound;

import cc.noxiuam.titanic.Titanic;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SoundUtil {

    public void playClick() {
        Titanic.getInstance().getBridge()
                .getMinecraftBridge()
                .bridge$getMinecraft()
                .sndManager.func_337_a("random.click", 1.0F, 1.0F);
    }

}
