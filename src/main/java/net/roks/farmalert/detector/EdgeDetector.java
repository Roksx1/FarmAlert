package net.roks.farmalert.detector;

import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

public final class EdgeDetector {

    /*
     * Temporary hardcoded coordinates.
     * These will come from the config later.
     */
    private static final double EDGE_X_MIN = -10.5;
    private static final double EDGE_X_MAX = 10.5;

    private static final double EDGE_Z_MIN = -10.5;
    private static final double EDGE_Z_MAX = 10.5;

    private static final double TOLERANCE = 0.15;

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

        // X Axis
        insideXMin = checkZone(
                position.withinX(EDGE_X_MIN, TOLERANCE),
                insideXMin
        );

        insideXMax = checkZone(
                position.withinX(EDGE_X_MAX, TOLERANCE),
                insideXMax
        );

        // Z Axis
        insideZMin = checkZone(
                position.withinZ(EDGE_Z_MIN, TOLERANCE),
                insideZMin
        );

        insideZMax = checkZone(
                position.withinZ(EDGE_Z_MAX, TOLERANCE),
                insideZMax
        );
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