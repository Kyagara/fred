package net.kyagara.fred.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyagara.fred.Fred;
import net.kyagara.fred.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModBlocks {
	private static void registerBlocks() {
		if (Fred.CONFIG.enableTheRockBlock()) {
			final Block THE_ROCK_BLOCK = new TheRockBlock(FabricBlockSettings.copy(Blocks.STONE));
			BlockItem blockItem = new BlockItem(THE_ROCK_BLOCK, new Item.Settings());
			ItemGroupEvents.modifyEntriesEvent(ModItems.FRED_BLOCK_GROUP).register(content -> content.add(blockItem));

			Registry.register(Registries.ITEM, new Identifier(Fred.MOD_ID, "the_rock_block"), blockItem);
			Registry.register(Registries.BLOCK, new Identifier(Fred.MOD_ID, "the_rock_block"), THE_ROCK_BLOCK);
			Registry.register(Registries.ITEM_GROUP, ModItems.FRED_BLOCK_GROUP, FabricItemGroup.builder().icon(() -> new ItemStack(THE_ROCK_BLOCK)).displayName(Text.translatable("itemGroup.fred.blocks")).build());
		}
	}

	public static void registerModBlocks() {
		Fred.LOGGER.debug("Registering blocks from " + Fred.MOD_ID);
		registerBlocks();
	}
}