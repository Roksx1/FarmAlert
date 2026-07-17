package net.roks.farmalert.service;

import net.roks.farmalert.config.FarmAlertConfig;
import net.roks.farmalert.config.io.ConfigManager;

/**
 * Service providing in-memory access and load/save operations for mod configurations.
 */
public final class ConfigService {

    // Cached configuration instance
    private static FarmAlertConfig config;

    private ConfigService() {
    }

    /**
     * Loads the config file into memory.
     */
    public static void load() {

        config = ConfigManager.load();

    }

    /**
     * Saves the current config to disk.
     */
    public static void save() {

        ConfigManager.save(config);

    }

    /**
     * Retrieves the current configuration, loading it if not cached.
     */
    public static FarmAlertConfig getConfig() {

        if (config == null) {
            load();
        }

        return config;

    }

}
