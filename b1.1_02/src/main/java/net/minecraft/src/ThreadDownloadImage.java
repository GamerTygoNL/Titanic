package net.minecraft.src;

import javax.imageio.ImageIO;
import java.net.HttpURLConnection;
import java.net.URL;

class ThreadDownloadImage extends Thread {

    final String location; /* synthetic field */
    final ImageBuffer buffer; /* synthetic field */
    final ThreadDownloadImageData imageData; /* synthetic field */
    ThreadDownloadImage(ThreadDownloadImageData threaddownloadimagedata, String s, ImageBuffer imagebuffer) {
        imageData = threaddownloadimagedata;
        location = s;
        buffer = imagebuffer;
    }

    public void run() {
        HttpURLConnection httpurlconnection = null;
        try {
            URL url = new URL(location);
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.addRequestProperty("User-Agent", "Mozilla/4.0");
            httpurlconnection.connect();
            if (httpurlconnection.getResponseCode() == 404) {
                return;
            }
            if (buffer == null) {
                imageData.image = ImageIO.read(httpurlconnection.getInputStream());
            } else {
                imageData.image = buffer.parseUserSkin(ImageIO.read(httpurlconnection.getInputStream()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            httpurlconnection.disconnect();
        }
    }
}
