package net.kyagara.fred.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyagara.fred.Main;
import net.kyagara.fred.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block THE_ROCK_BLOCK = registerBlock("the_rock_block",
            new TheRockBlock(FabricBlockSettings.of(Material.STONE)), ModItems.FRED_GROUP);

    // Registers a Block
    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings().group(tab));

        Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), blockItem);

        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        Main.LOGGER.debug("Registering blocks for " + Main.MOD_ID);
    }
}