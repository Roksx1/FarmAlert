package net.roks.farmalert;

import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.config.screen.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.detector.EdgeDetector;
import net.roks.farmalert.detector.TeleportDetector;
import net.roks.farmalert.keybind.KeyBindings;

public class FarmAlertClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigService.load();
        KeyBindings.register();

        FarmAlert.LOGGER.info("FarmAlert client loaded.");

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {

            PositionService.update();

            while (KeyBindings.OPEN_CONFIG.consumeClick()) {

                minecraft.setScreen(
                        ConfigScreen.create(
                                minecraft.screen
                        )
                );

            }

            if (minecraft.player == null) {
                return;
            }

            EdgeDetector.check();

            TeleportDetector.check();

        });

    }
}