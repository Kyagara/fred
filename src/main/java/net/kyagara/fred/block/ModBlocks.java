package net.kyagara.fred.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyagara.fred.Main;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    private static Block THE_ROCK_BLOCK = new TheRockBlock(FabricBlockSettings.of(Material.STONE));
    private static Block REI_FUMO_BLOCK = new ReiFumoBlock(FabricBlockSettings.of(Material.WOOL));

    private static void registerBlocks() {
        if (FredConfig.enableTheRockBlock) {
            registerBlock("the_rock_block", THE_ROCK_BLOCK);
        }

        if (FredConfig.enableReiFumoBlock) {
            registerBlock("rei_fumo_block", REI_FUMO_BLOCK);
        }
    }

    // Registers a Block and its BlockItem
    private static void registerBlock(String name, Block block) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings().group(ModItems.FRED_GROUP));

        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), blockItem);

        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        Main.LOGGER.debug("Registering blocks for " + Main.MOD_ID);
        registerBlocks();
    }
}