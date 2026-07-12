package net.roks.farmalert.config;

public class FarmAlertConfig {

    public final EdgeConfig edge = new EdgeConfig();

    public final TeleportConfig teleport = new TeleportConfig();

    /*
     * Future milestones
     */
    public boolean debugMode = false;

    public boolean playSound = true;

    public int titleDuration = 40;

    public float titleScale = 1.0F;

}