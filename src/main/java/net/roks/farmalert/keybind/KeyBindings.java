package net.roks.farmalert.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.roks.farmalert.FarmAlert;
import org.lwjgl.glfw.GLFW;

/**
 * Registers and manages custom keyboard mappings for the mod.
 */
public final class KeyBindings {

    private KeyBindings() {
    }

    // Key binding to open config screen
    public static KeyMapping OPEN_CONFIG;

    /**
     * Registers the mod's keybindings with the Fabric registry.
     */
    public static void register() {

        KeyMapping.Category category =
                KeyMapping.Category.register(
                        FarmAlert.id("keybinds")
                );

        OPEN_CONFIG =
                KeyMappingHelper.registerKeyMapping(
                        new KeyMapping(
                                "key.farmalert.open_config",
                                InputConstants.Type.KEYSYM,
                                GLFW.GLFW_KEY_UNKNOWN,
                                category
                        )
                );
    }
}
