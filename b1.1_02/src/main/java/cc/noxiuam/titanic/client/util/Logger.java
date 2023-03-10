package cc.noxiuam.titanic.client.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Logger {

    private static final String LOG_MESSAGE_FORMAT = "[Titanic] [%s] (%s) %s";

    private final String section;

    private void log(String level, String msg) {
        String formattedMsg = String.format(LOG_MESSAGE_FORMAT, level, this.section, msg);
        System.out.println(formattedMsg);
    }

    public void info(String msg) {
        log("INFO", msg);
    }

    public void debug(String msg) {
        log("DEBUG", msg);
    }

    public void error(String msg) {
        log("ERROR", msg);
    }

}
