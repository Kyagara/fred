package net.kyagara.fred.stat;

import net.kyagara.fred.Main;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatistics {
    public static final Identifier DOOT_COUNT = registerStatistic("doot_count");
    public static final Identifier ROCK_COUNT = registerStatistic("rock_count");
    public static final Identifier MY_MOVIE_COUNT = registerStatistic("my_movie_count");
    public static final Identifier BLOCK_BREAK_COUNT = registerStatistic("block_break_count");

    private static Identifier registerStatistic(String name) {
        Identifier identifier = new Identifier(Main.MOD_ID, name);

        Registry.register(Registry.CUSTOM_STAT, name, identifier);

        Stats.CUSTOM.getOrCreateStat(identifier, StatFormatter.DEFAULT);

        return identifier;
    }

    public static void registerModStatistics() {
        Main.LOGGER.debug("Registering stats from " + Main.MOD_ID);
    }
}