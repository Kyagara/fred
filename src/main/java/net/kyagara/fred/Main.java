package net.kyagara.fred;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.kyagara.fred.items.ModItems;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stats.ModStats;

public class Main implements ModInitializer {
	public static final String MOD_ID = "fred";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModSounds.registerModSounds();
		ModStats.registerModStats();
	}
}
