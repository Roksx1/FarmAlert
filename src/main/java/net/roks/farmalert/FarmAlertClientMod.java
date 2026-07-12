package net.roks.farmalert;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.detector.EdgeDetector;
import net.roks.farmalert.detector.TeleportDetector;

import net.roks.farmalert.service.TitleService;

public class FarmAlertClientMod implements ClientModInitializer {

    private int ticks = 0;

    @Override
    public void onInitializeClient() {

        FarmAlert.LOGGER.info("FarmAlert client loaded.");

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {

            PositionService.update();

            if (minecraft.player == null) {
                return;
            }

            EdgeDetector.check();

            TeleportDetector.check();

        });

    }
}