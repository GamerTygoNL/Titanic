package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.net.ConnectException;
import java.net.UnknownHostException;

class ThreadConnectToServer extends Thread {

    final Minecraft mc; /* synthetic field */
    final String hostName; /* synthetic field */
    final int port; /* synthetic field */
    final GuiConnecting connectingGui; /* synthetic field */
    ThreadConnectToServer(GuiConnecting guiconnecting, Minecraft minecraft, String s, int i) {
        connectingGui = guiconnecting;
        mc = minecraft;
        hostName = s;
        port = i;
    }

    public void run() {
        try {
            GuiConnecting.setNetClientHandler(connectingGui, new NetClientHandler(mc, hostName, port));
            if (GuiConnecting.isCancelled(connectingGui)) {
                return;
            }
            GuiConnecting.getNetClientHandler(connectingGui).addToSendQueue(new Packet2Handshake(mc.session.playerName));
        } catch (UnknownHostException unknownhostexception) {
            if (GuiConnecting.isCancelled(connectingGui)) {
                return;
            }
            mc.displayGuiScreen(new GuiConnectFailed("connect.failed", "disconnect.genericReason", new Object[]{
                    "Unknown host '" + hostName + "'"
            }));
        } catch (ConnectException connectexception) {
            if (GuiConnecting.isCancelled(connectingGui)) {
                return;
            }
            mc.displayGuiScreen(new GuiConnectFailed("connect.failed", "disconnect.genericReason", new Object[]{
                    connectexception.getMessage()
            }));
        } catch (Exception exception) {
            if (GuiConnecting.isCancelled(connectingGui)) {
                return;
            }
            exception.printStackTrace();
            mc.displayGuiScreen(new GuiConnectFailed("connect.failed", "disconnect.genericReason", new Object[]{
                    exception.toString()
            }));
        }
    }
}
