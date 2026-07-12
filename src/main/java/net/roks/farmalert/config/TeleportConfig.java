package net.roks.farmalert.config;

import net.roks.farmalert.constant.Constants;

public class TeleportConfig {

    public boolean enabled = true;

    public double x = 0.0;
    public double y = 0.0;
    public double z = 0.0;

    public double tolerance = Constants.DEFAULT_TELEPORT_TOLERANCE;

    public String command = "";

}