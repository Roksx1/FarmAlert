package net.roks.farmalert.detector;

import net.roks.farmalert.model.Position;
import net.roks.farmalert.service.PositionService;
import net.roks.farmalert.service.TitleService;

public final class EdgeDetector {

    /*
     * Temporary hardcoded coordinates.
     * These will come from the config later.
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

        checkLeft(position);
        checkRight(position);

    }

    private static void checkLeft(Position position) {

        boolean inside = position.withinX(LEFT_X, TOLERANCE);

        if (!inside) {
            insideLeftZone = false;
            return;
        }

        if (insideLeftZone) {
            return;
        }

        insideLeftZone = true;

        TitleService.showEndOfFarm();

    }

    private static void checkRight(Position position) {

        boolean inside = position.withinX(RIGHT_X, TOLERANCE);

        if (!inside) {
            insideRightZone = false;
            return;
        }

        if (insideRightZone) {
            return;
        }

        insideRightZone = true;

        TitleService.showEndOfFarm();

    }

}