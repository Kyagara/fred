package net.kyagara.fred;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.kyagara.fred.block.ModBlocks;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.item.ModItems;
import net.kyagara.fred.packets.ModPackets;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stat.ModStats;

public class Main implements ModInitializer {
	public static final String MOD_ID = "fred";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FredConfig.init(MOD_ID, FredConfig.class);

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModStats.registerModStats();
		ModSounds.registerModSounds();
		ModCommands.registerModCommands();
		ModPackets.registerC2SPackets();
	}
}
