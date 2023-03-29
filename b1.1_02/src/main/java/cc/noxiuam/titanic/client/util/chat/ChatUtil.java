package cc.noxiuam.titanic.client.util.chat;

import cc.noxiuam.titanic.Ref;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatUtil {

    public void addChatMessage(String msg) {
        if (Ref.getMinecraft().theWorld != null) {
            Ref.getMinecraft().ingameGUI.addChatMessage(ChatColor.DARK_AQUA + "[Titanic] " + ChatColor.WHITE + msg);
        }
    }

}
