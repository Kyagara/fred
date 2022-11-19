package net.kyagara.fred.stat;

import net.kyagara.fred.Main;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStats {
    public static final Identifier DOOT_COUNT = registerStats("doot_count");

    private static Identifier registerStats(String name) {
        Identifier identifier = new Identifier(Main.MOD_ID, name);

        Registry.register(Registry.CUSTOM_STAT, name, identifier);

        Stats.CUSTOM.getOrCreateStat(identifier, StatFormatter.DEFAULT);

        return identifier;
    }

    public static void registerModStats() {
        Main.LOGGER.debug("Registering stats from " + Main.MOD_ID);
    }
}