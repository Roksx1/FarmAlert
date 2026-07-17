package net.roks.farmalert.constant;

/**
 * Global constant definitions used throughout the mod.
 */
public final class Constants {

    private Constants() {
    }

    // Translation keys
    public static final String KEY_TITLE_END_OF_FARM = "farmalert.title.end_of_farm";
    public static final String KEY_TITLE_TELEPORT_NOW = "farmalert.title.teleport_now";


    // Default overlay timings (in ticks)
    public static final int DEFAULT_TITLE_FADE_IN = 0;
    public static final int DEFAULT_TITLE_STAY = 40;
    public static final int DEFAULT_TITLE_FADE_OUT = 0;

    // Default detection tolerances
    public static final double DEFAULT_EDGE_TOLERANCE = 0.15;
    public static final double DEFAULT_TELEPORT_TOLERANCE = 0.5;

    // Debug options
    public static final int DEBUG_DECIMAL_PLACES = 4;
}
