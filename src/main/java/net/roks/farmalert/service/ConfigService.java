package net.roks.farmalert.service;

import net.roks.farmalert.config.FarmAlertConfig;

public final class ConfigService {

    private static final FarmAlertConfig CONFIG = new FarmAlertConfig();

    private ConfigService() {
    }

    public static FarmAlertConfig getConfig() {
        return CONFIG;
    }

}