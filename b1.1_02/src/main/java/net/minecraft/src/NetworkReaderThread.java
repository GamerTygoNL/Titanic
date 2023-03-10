package net.minecraft.src;

class NetworkReaderThread extends Thread {

    final NetworkManager netManager; /* synthetic field */

    NetworkReaderThread(NetworkManager networkmanager, String s) {
        super(s);
        netManager = networkmanager;
    }

    public void run() {
        synchronized (NetworkManager.threadSyncObject) {
            NetworkManager.numReadThreads++;
        }
        try {
            for (; NetworkManager.isRunning(netManager) && !NetworkManager.isServerTerminating(netManager); NetworkManager.readNetworkPacket(netManager)) {
            }
        } finally {
            synchronized (NetworkManager.threadSyncObject) {
                NetworkManager.numReadThreads--;
            }
        }
    }
}
