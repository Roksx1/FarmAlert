package net.roks.farmalert.config;

/**
 * Represents a single crop profile containing its custom boundaries and teleport options.
 */
public class Profile {

    // Boundary detection settings
    public EdgeConfig edge = new EdgeConfig();

    // Teleport points and command settings
    public TeleportConfig teleport = new TeleportConfig();

}
