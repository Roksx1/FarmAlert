package net.roks.farmalert.service;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import net.roks.farmalert.model.Position;

/**
 * Service to track and retrieve the player's current coordinate position.
 */
public final class PositionService {

    // Cached player coordinates
    private static Position currentPosition = new Position(0, 0, 0);

    private PositionService() {
    }

    /**
     * Updates the cached position based on active player coordinates.
     */
    public static void update() {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft == null) {
            return;
        }

        Player player = minecraft.player;

        if (player == null) {
            return;
        }

        currentPosition = new Position(
                player.getX(),
                player.getY(),
                player.getZ()
        );
    }

    /**
     * Gets the last tracked player position.
     */
    public static Position getCurrentPosition() {
        return currentPosition;
    }
}
