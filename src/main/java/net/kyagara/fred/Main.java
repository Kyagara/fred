package net.kyagara.fred;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.kyagara.fred.blocks.ModBlocks;
import net.kyagara.fred.commands.ModCommands;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.items.ModItems;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stats.ModStats;

public class Main implements ModInitializer {
	public static final String MOD_ID = "fred";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FredConfig.init(MOD_ID, FredConfig.class);

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModSounds.registerModSounds();
		ModStats.registerModStats();
		ModCommands.registerModCommands();
	}
}
