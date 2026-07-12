package net.roks.farmalert.detector;

import net.minecraft.client.Minecraft;
import net.roks.farmalert.service.TitleService;
import net.roks.farmalert.config.TeleportConfig;
import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.service.PositionService;


public final class TeleportDetector {

    private static boolean armed = true;

    private TeleportDetector() {
    }

    public static void check() {

        TeleportConfig config = ConfigService.getConfig().teleport;

        if (!config.enabled) {
            armed = true;
            return;
        }

        Position position = PositionService.getCurrentPosition();

        boolean inside = position.withinXYZ(
                new Position(
                        config.targetX,
                        config.targetY,
                        config.targetZ
                ),
                config.xTolerance,
                config.yTolerance,
                config.zTolerance
        );

        if (inside) {

            if (armed) {

                armed = false;

                TitleService.showTeleportNow();

                Minecraft minecraft = Minecraft.getInstance();

                if (minecraft.player != null) {

                    String command = config.command.trim();

                    if (!command.isEmpty()) {

                        if (command.startsWith("/")) {
                            command = command.substring(1);
                        }

                        minecraft.player.connection.sendCommand(command);

                    }

                }

            }

        } else {

            armed = true;

        }

    }
}