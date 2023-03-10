package net.minecraft.src;

class ThreadRunIsoClient extends Thread {

    final CanvasIsomPreview isoCanvas; /* synthetic field */

    ThreadRunIsoClient(CanvasIsomPreview canvasisompreview) {
        isoCanvas = canvasisompreview;
    }

    public void run() {
        while (CanvasIsomPreview.isRunning(isoCanvas)) {
            isoCanvas.func_1265_d();
            try {
                Thread.sleep(1L);
            } catch (Exception exception) {
            }
        }
    }
}
