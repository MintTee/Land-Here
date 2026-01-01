package land_here;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Land Here");

    @Override
    public void onInitialize() {
        LOGGER.info("Land Here for MC 1.21 initializing");
    }
}