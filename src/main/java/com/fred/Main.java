package com.fred;

import com.fred.blocks.TheRockBlock;
import com.fred.items.Trumpet;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class Main {
	public static final String MOD_ID = "fred";

	private static final DeferredRegister<Identifier> STATS = DeferredRegister.create(MOD_ID, RegistryKeys.CUSTOM_STAT);

	public static final RegistrySupplier<Identifier> DOOT_COUNT = STATS.register("doot_count",
			() -> Identifier.of(Main.MOD_ID, "doot_count"));
	public static final RegistrySupplier<Identifier> ROCK_COUNT = STATS.register("rock_count",
			() -> Identifier.of(Main.MOD_ID, "rock_count"));
	private static final RegistrySupplier<Identifier> BLOCK_BREAK_COUNT = STATS.register("block_break_count",
			() -> Identifier.of(Main.MOD_ID, "block_break_count"));
	private static final RegistrySupplier<Identifier> BLOCK_PLACED_COUNT = STATS.register("block_placed_count",
			() -> Identifier.of(Main.MOD_ID, "block_placed_count"));

	private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, RegistryKeys.SOUND_EVENT);

	public static final RegistrySupplier<SoundEvent> TRUMPET_USE = SOUNDS.register("item.trumpet.use",
			() -> SoundEvent.of(Identifier.of(MOD_ID, "item.trumpet.use")));
	public static final RegistrySupplier<SoundEvent> THE_ROCK_BLOCK_SCARE = SOUNDS.register("block.the_rock_block",
			() -> SoundEvent.of(Identifier.of(MOD_ID, "block.the_rock_block")));

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);

	public static void init() {
		Configuration.load(Platform.getConfigFolder().resolve("fred.json").toString());

		BlockEvent.PLACE.register((level, pos, state, placer) -> {
			if (placer != null && placer.isPlayer()) {
				((PlayerEntity) placer).incrementStat(Main.BLOCK_PLACED_COUNT.get());
			}

			return EventResult.pass();
		});

		BlockEvent.BREAK.register((level, blockPos, state, breaker, xp) -> {
			breaker.incrementStat(Main.BLOCK_BREAK_COUNT.get());
			return EventResult.pass();
		});

		if (Configuration.enableTheRockBlock()) {
			BLOCKS.register("the_rock_block", () -> new TheRockBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
			ITEMS.register("the_rock_block", () -> new BlockItem(BLOCKS.getRegistrar().get(Identifier.of(MOD_ID, "the_rock_block")), new Item.Settings()));
		}

		if (Configuration.enableTrumpet()) {
			ITEMS.register("trumpet", () -> new Trumpet(new Item.Settings()));
		}

		//? if neoforge {
		/*STATS.register();
		SOUNDS.register();
		ITEMS.register();
		BLOCKS.register();
		*///?}
	}
}
