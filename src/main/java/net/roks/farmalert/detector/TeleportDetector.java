package net.roks.farmalert.detector;

import net.minecraft.client.Minecraft;

import net.roks.farmalert.config.TeleportConfig;
import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

/**
 * Detects if the player is at the teleport point and schedules the teleport command.
 */
public final class TeleportDetector {

    // Armed state to prevent repeated triggers
    private static boolean armed = true;

    // Command delay scheduler state
    private static boolean waitingToExecute = false;
    private static long executeAt = 0L;

    private TeleportDetector() {
    }

    /**
     * Periodically monitors player coordinate position against teleport settings.
     */
    public static void check() {

        TeleportConfig config = ConfigService.getConfig().getActiveProfile().teleport;

        // Reset state if disabled
        if (!config.enabled) {
            armed = true;
            waitingToExecute = false;
            return;
        }

        Position position = PositionService.getCurrentPosition();

        // Check if player is within target coordinates range
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
                waitingToExecute = true;
                executeAt = System.currentTimeMillis()
                        + (long) (config.commandDelay * 1000.0);

                TitleService.showTeleportNow();

            }

        } else {

            // Re-arm when player leaves zone
            armed = true;

        }

        // Handle scheduled command execution
        if (waitingToExecute
                && System.currentTimeMillis() >= executeAt) {

            waitingToExecute = false;

            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.player != null) {

                String command = config.command.trim();

                if (!command.isEmpty()) {

                    // Format slash commands appropriately
                    if (command.startsWith("/")) {
                        command = command.substring(1);
                    }

                    minecraft.player.connection.sendCommand(command);

                }

            }

        }

    }

}
