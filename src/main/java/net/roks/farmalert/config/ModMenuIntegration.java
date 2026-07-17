package net.roks.farmalert.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screens.Screen;
import net.roks.farmalert.config.screen.ConfigScreen;

/**
 * Mod Menu integration for opening the config screen.
 */
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Return the config screen factory to open Cloth Config screen
        return parent -> ConfigScreen.create(parent);
    }
}
