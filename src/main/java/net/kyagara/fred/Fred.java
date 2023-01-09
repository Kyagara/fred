package net.kyagara.fred;

import net.fabricmc.api.ModInitializer;
import net.kyagara.fred.block.ModBlocks;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.item.ModItems;
import net.kyagara.fred.packet.ModPackets;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stat.ModStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fred implements ModInitializer {
	public static final String MOD_ID = "fred";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final net.kyagara.fred.FredConfig CONFIG = net.kyagara.fred.FredConfig.createAndLoad();

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModStatistics.registerModStatistics();
		ModSounds.registerModSounds();
		ModCommands.registerModCommands();
		ModPackets.registerModPackets();
	}
}
