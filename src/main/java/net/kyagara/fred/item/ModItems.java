package net.kyagara.fred.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kyagara.fred.Fred;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final RegistryKey<ItemGroup> FRED_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("fred", "items"));
	public static final RegistryKey<ItemGroup> FRED_BLOCK_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("fred", "blocks"));

	private static void registerItems() {
		if (Fred.CONFIG.enableTrumpet()) {
			final Item TRUMPET_ITEM = new TrumpetItem(new FabricItemSettings());
			ItemGroupEvents.modifyEntriesEvent(FRED_ITEM_GROUP).register(content -> content.add(TRUMPET_ITEM));

			Registry.register(Registries.ITEM, new Identifier(Fred.MOD_ID, "trumpet"), TRUMPET_ITEM);
			Registry.register(Registries.ITEM_GROUP, FRED_ITEM_GROUP, FabricItemGroup.builder().icon(() -> new ItemStack(TRUMPET_ITEM)).displayName(Text.translatable("itemGroup.fred.items")).build());
		}
	}

	public static void registerModItems() {
		Fred.LOGGER.debug("Registering items from " + Fred.MOD_ID);
		registerItems();
	}
}