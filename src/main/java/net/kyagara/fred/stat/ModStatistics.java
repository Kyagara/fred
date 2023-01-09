package net.kyagara.fred.stat;

import net.kyagara.fred.Fred;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatistics {
	public static final Identifier DOOT_COUNT = registerStatistic("doot_count");
	public static final Identifier ROCK_COUNT = registerStatistic("rock_count");
	public static final Identifier QUASO_COUNT = registerStatistic("quaso_count");
	public static final Identifier MY_MOVIE_COUNT = registerStatistic("my_movie_count");
	public static final Identifier BLOCK_BREAK_COUNT = registerStatistic("block_break_count");
	public static final Identifier BLOCK_PLACED_COUNT = registerStatistic("block_placed_count");

	private static Identifier registerStatistic(String name) {
		Identifier identifier = new Identifier(Fred.MOD_ID, name);

		Registry.register(Registry.CUSTOM_STAT, name, identifier);

		Stats.CUSTOM.getOrCreateStat(identifier, StatFormatter.DEFAULT);

		return identifier;
	}

	public static void registerModStatistics() {
		Fred.LOGGER.debug("Registering stats from " + Fred.MOD_ID);
	}
}