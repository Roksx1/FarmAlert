package net.roks.farmalert.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global configuration layout for the FarmAlert mod.
 */
public class FarmAlertConfig {

    // Currently active crop profile name
    public String activeProfile = "default";

    // Saved crop profiles mapped by custom names
    public Map<String, Profile> profiles = new LinkedHashMap<>();

    // Debug and UI properties
    public boolean debugMode = false;
    public boolean playSound = true;
    public int titleDuration = 40;
    public float titleScale = 1.0F;

    public FarmAlertConfig() {
        // Initialize with default profile
        profiles.put("default", new Profile());
    }

    /**
     * Gets the active crop profile. Ensures a valid profile is always returned.
     */
    public Profile getActiveProfile() {
        if (profiles.isEmpty()) {
            profiles.put("default", new Profile());
        }
        Profile active = profiles.get(activeProfile);
        if (active == null) {
            activeProfile = profiles.keySet().iterator().next();
            active = profiles.get(activeProfile);
        }
        return active;
    }

}
