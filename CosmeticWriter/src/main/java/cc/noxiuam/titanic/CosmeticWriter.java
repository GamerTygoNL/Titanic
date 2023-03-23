package cc.noxiuam.titanic;

public class CosmeticWriter {

    private static final String CAPE_DIR = "https://noxiuam.cc/titanic-client/cosmetic/cape/";

    public CosmeticWriter() {
        String name = "AP_DEFAULT";

        new ProfileWriter(name, name, "Empty description.", CAPE_DIR + name + ".png").writeProfile();
    }

}
