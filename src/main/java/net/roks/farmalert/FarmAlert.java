package net.roks.farmalert;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entrypoint for the FarmAlert mod on the server side / common environment.
 */
public class FarmAlert implements ModInitializer {
	public static final String MOD_ID = "farmalert";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Runs during mod initialization
		LOGGER.info("FarmAlert common initialized!");
	}

	/**
	 * Creates a namespace identifier for this mod.
	 */
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
