package net.roks.farmalert.detector;

import net.roks.farmalert.config.EdgeConfig;
import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

public final class EdgeDetector {



    /*
     * Trigger state
     */
    private static boolean insideXMin = false;
    private static boolean insideXMax = false;

    private static boolean insideZMin = false;
    private static boolean insideZMax = false;

    private EdgeDetector() {
    }

    public static void check() {

        Position position = PositionService.getCurrentPosition();
        EdgeConfig config = ConfigService.getConfig().edge;

        // X Axis
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
        // Z Axis

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