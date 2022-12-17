package net.kyagara.fred.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kyagara.fred.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    // Main item group
    public static final ItemGroup FRED_GROUP = FabricItemGroupBuilder.build(
            new Identifier("fred", "general"), () -> new ItemStack(ModItems.TRUMPET_ITEM));

    // Trumpet
    public static final Item TRUMPET_ITEM = registerItem("trumpet",
            new TrumpetItem(new FabricItemSettings().group(FRED_GROUP).rarity(Rarity.UNCOMMON).maxDamage(200)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Main.LOGGER.debug("Registering items from " + Main.MOD_ID);
    }
}