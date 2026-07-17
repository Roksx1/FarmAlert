package net.roks.farmalert.config;

import net.roks.farmalert.constant.Constants;

/**
 * Configuration options for the farm edge detector.
 */
public class EdgeConfig {

    // Activation switches
    public boolean xEnabled = false;
    public boolean zEnabled = false;

    // Coordinate boundaries
    public double edgeXMin = -10.5;
    public double edgeXMax = 10.5;

    public double edgeZMin = -10.5;
    public double edgeZMax = 10.5;

    // Precision tolerances
    public double xTolerance = Constants.DEFAULT_EDGE_TOLERANCE;
    public double zTolerance = Constants.DEFAULT_EDGE_TOLERANCE;

}
