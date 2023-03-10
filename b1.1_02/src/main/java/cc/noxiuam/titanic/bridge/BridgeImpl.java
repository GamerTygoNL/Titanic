package cc.noxiuam.titanic.bridge;

import cc.noxiuam.titanic.client.util.Logger;

public class BridgeImpl implements IBridge {

    private final Logger logger = new Logger("Bridge Implementation");

    @Override
    public void onEnable() {

        logger.info("Setup Bridge Implementation");

    }

}
