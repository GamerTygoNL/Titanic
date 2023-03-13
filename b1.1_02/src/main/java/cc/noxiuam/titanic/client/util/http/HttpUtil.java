package cc.noxiuam.titanic.client.util.http;

import lombok.experimental.UtilityClass;

import java.net.URL;

@UtilityClass
public class HttpUtil {

    /**
     * Checks if a URL is valid or not.
     *
     * @param url The url to check
     */
    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
