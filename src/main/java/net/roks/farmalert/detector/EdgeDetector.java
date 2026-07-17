package net.roks.farmalert.detector;

import net.roks.farmalert.config.EdgeConfig;
import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

/**
 * Detects if the player reaches the boundaries of the farm on X and Z axes.
 */
public final class EdgeDetector {

    // Tracks if player was already inside the trigger zone on previous ticks to prevent repeat alerts
    private static boolean insideXMin = false;
    private static boolean insideXMax = false;
    private static boolean insideZMin = false;
    private static boolean insideZMax = false;

    private EdgeDetector() {
    }

    /**
     * Checks if the player is currently boundary-crossing.
     */
    public static void check() {

        Position position = PositionService.getCurrentPosition();
        EdgeConfig config = ConfigService.getConfig().getActiveProfile().edge;

        // X Axis edge check
        if (config.xEnabled) {
            insideXMin = checkZone(
                    position.withinX(
                            config.edgeXMin,
                            config.xTolerance
                    ),
                    insideXMin
            );

            insideXMax = checkZone(
                    position.withinX(
                            config.edgeXMax,
                            config.xTolerance
                    ),
                    insideXMax
            );
        }

        // Z Axis edge check
        if (config.zEnabled) {
            insideZMin = checkZone(
                    position.withinZ(
                            config.edgeZMin,
                            config.zTolerance
                    ),
                    insideZMin
            );

            insideZMax = checkZone(
                    position.withinZ(
                            config.edgeZMax,
                            config.zTolerance
                    ),
                    insideZMax
            );
        }
    }

    /**
     * Updates alert state and triggers notification on initial entry to target zone.
     */
    private static boolean checkZone(boolean inside, boolean alreadyInside) {

        if (!inside) {
            return false;
        }

        if (alreadyInside) {
            return true;
        }

        TitleService.showEndOfFarm();

        return true;
    }
}
