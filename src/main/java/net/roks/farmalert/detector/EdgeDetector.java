package net.roks.farmalert.detector;

import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

public final class EdgeDetector {

    /*
     * Temporary hardcoded coordinates.
     * These will move to the config later.
     */
    private static final double LEFT_X = -10.5;
    private static final double RIGHT_X = 10.5;

    private static final double TOLERANCE = 0.15;

    /*
     * Trigger state
     */
    private static boolean insideLeftZone = false;
    private static boolean insideRightZone = false;

    private EdgeDetector() {
    }

    public static void check() {

        Position position = PositionService.getCurrentPosition();

        insideLeftZone = checkZone(
                position.withinX(LEFT_X, TOLERANCE),
                insideLeftZone
        );

        insideRightZone = checkZone(
                position.withinX(RIGHT_X, TOLERANCE),
                insideRightZone
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