package net.roks.farmalert.service;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import net.roks.farmalert.constant.Constants;

/**
 * Service to show custom overlay titles/alerts on the player's screen.
 */
public final class TitleService {

    private TitleService() {
    }

    private static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    /**
     * Shows a title message with specified fade and stay durations.
     */
    public static void show(Component title,
                            int fadeIn,
                            int stay,
                            int fadeOut) {

        Minecraft minecraft = getMinecraft();

        if (minecraft == null || minecraft.player == null) {
            return;
        }

        minecraft.gui.hud.setTimes(fadeIn, stay, fadeOut);
        minecraft.gui.hud.setTitle(title);
    }

    /**
     * Shows a title message with default timings.
     */
    public static void show(Component title) {

        show(
                title,
                Constants.DEFAULT_TITLE_FADE_IN,
                Constants.DEFAULT_TITLE_STAY,
                Constants.DEFAULT_TITLE_FADE_OUT
        );

    }

    /**
     * Displays the "End of Farm" alert.
     */
    public static void showEndOfFarm() {

        show(Component.translatable(Constants.KEY_TITLE_END_OF_FARM));

    }

    /**
     * Displays the "Teleport Now" alert.
     */
    public static void showTeleportNow() {

        show(Component.translatable(Constants.KEY_TITLE_TELEPORT_NOW));

    }
}
