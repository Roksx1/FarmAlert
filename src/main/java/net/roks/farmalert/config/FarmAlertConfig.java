package net.roks.farmalert.config;

/**
 * Global configuration layout for the FarmAlert mod.
 */
public class FarmAlertConfig {

    // Config sections
    public EdgeConfig edge = new EdgeConfig();
    public TeleportConfig teleport = new TeleportConfig();

    // Debug and UI properties
    public boolean debugMode = false;
    public boolean playSound = true;
    public int titleDuration = 40;
    public float titleScale = 1.0F;

}
