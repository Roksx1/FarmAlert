package net.roks.farmalert;

import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.config.screen.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.detector.EdgeDetector;
import net.roks.farmalert.detector.TeleportDetector;
import net.roks.farmalert.keybind.KeyBindings;

/**
 * Client entrypoint for FarmAlert. Handles initialization and client tick loops.
 */
public class FarmAlertClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Load configuration and register keys
        ConfigService.load();
        KeyBindings.register();

        FarmAlert.LOGGER.info("FarmAlert client loaded.");

        // Monitor player actions and tick updates client-side
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            // Keep current position updated
            PositionService.update();

            // Open config screen if hotkey is pressed
            while (KeyBindings.OPEN_CONFIG.consumeClick()) {
                minecraft.gui.setScreen (
                        ConfigScreen.create(
                                minecraft.gui.screen ()
                        )
                );
            }

            // Perform safety check
            if (minecraft.player == null) {
                return;
            }

            // Run detection algorithms
            EdgeDetector.check();
            TeleportDetector.check();
        });
    }
}
