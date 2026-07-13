package net.roks.farmalert.config;

import net.roks.farmalert.constant.Constants;

public class EdgeConfig {

    public boolean xEnabled = false;
    public boolean zEnabled = false;

    public double edgeXMin = -10.5;
    public double edgeXMax = 10.5;

    public double edgeZMin = -10.5;
    public double edgeZMax = 10.5;

    public double xTolerance = Constants.DEFAULT_EDGE_TOLERANCE;
    public double zTolerance = Constants.DEFAULT_EDGE_TOLERANCE;

}