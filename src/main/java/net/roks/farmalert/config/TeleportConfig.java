package net.roks.farmalert.config;

import net.roks.farmalert.constant.Constants;

/**
 * Configuration options for the teleport detector.
 */
public class TeleportConfig {

    // Activation switch
    public boolean enabled = false;

    // Target teleport point coordinates
    public double targetX = 0.0;
    public double targetY = 0.0;
    public double targetZ = 0.0;

    // XYZ tolerance thresholds
    public double xTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;
    public double yTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;
    public double zTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;

    // Command parameters
    public double commandDelay = 2.0;
    public String command = "";

}
