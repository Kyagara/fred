package com.fred;

import com.fred.blocks.TheRockBlock;
import com.fred.items.Trumpet;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class Main {
	public static final String MOD_ID = "fred";
	public static final com.fred.FredConfig CONFIG = com.fred.FredConfig.createAndLoad();

	public static final Identifier DOOT_COUNT = registerStatistic("doot_count");
	public static final Identifier ROCK_COUNT = registerStatistic("rock_count");
	public static final Identifier BLOCK_BREAK_COUNT = registerStatistic("block_break_count");
	public static final Identifier BLOCK_PLACED_COUNT = registerStatistic("block_placed_count");

	public static final SoundEvent TRUMPET_USE = registerSound("item.trumpet.use");
	public static final SoundEvent THE_ROCK_BLOCK_SCARE = registerSound("block.the_rock_block");

	public static void init() {
		BlockEvent.PLACE.register((level, pos, state, placer) -> {
			if (placer != null && placer.isPlayer()) {
				((PlayerEntity) placer).incrementStat(Main.BLOCK_PLACED_COUNT);
			}

			return EventResult.pass();
		});

		BlockEvent.BREAK.register((level, blockPos, state, breaker, xp) -> {
			breaker.incrementStat(Main.BLOCK_BREAK_COUNT);
			return EventResult.pass();
		});

		if (Main.CONFIG.enableTheRockBlock()) {
			final Block THE_ROCK_BLOCK = new TheRockBlock(AbstractBlock.Settings.copy(Blocks.STONE));
			BlockItem blockItem = new BlockItem(THE_ROCK_BLOCK, new Item.Settings());

			Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, "the_rock_block"), blockItem);
			Registry.register(Registries.BLOCK, Identifier.of(Main.MOD_ID, "the_rock_block"), THE_ROCK_BLOCK);
		}

		if (Main.CONFIG.enableTrumpet()) {
			final Item TRUMPET_ITEM = new Trumpet(new Item.Settings());
			Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, "trumpet"), TRUMPET_ITEM);
		}
	}

	private static SoundEvent registerSound(String name) {
		Identifier identifier = Identifier.of(Main.MOD_ID, name);
		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	private static Identifier registerStatistic(String name) {
		Identifier identifier = Identifier.of(Main.MOD_ID, name);
		Registry.register(Registries.CUSTOM_STAT, name, identifier);
		Stats.CUSTOM.getOrCreateStat(identifier, StatFormatter.DEFAULT);
		return identifier;
	}
}
