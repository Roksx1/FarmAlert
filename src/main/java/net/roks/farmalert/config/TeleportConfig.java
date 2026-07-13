package net.roks.farmalert.config;

import net.roks.farmalert.constant.Constants;

public class TeleportConfig {

    public boolean enabled = true;

    public double targetX = 0.0;
    public double targetY = 0.0;
    public double targetZ = 0.0;

    public double xTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;
    public double yTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;
    public double zTolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;

    public double commandDelay = 0.0;
    public String command = "garden";

}