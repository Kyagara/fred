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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
	private static final Item TRUMPET_ITEM = new TrumpetItem(new FabricItemSettings());

	// Main item group
	public static final ItemGroup FRED_GROUP = FabricItemGroup.builder(new Identifier("fred", "general"))
			.displayName(Text.translatable("itemGroup.fred.general"))
			.icon(() -> new ItemStack(TRUMPET_ITEM))
			.build();

	private static void registerItems() {
		if (Fred.CONFIG.enableTrumpet()) {
			registerItem("trumpet", TRUMPET_ITEM);
		}
	}

	private static void registerItem(String name, Item item) {
		ItemGroupEvents.modifyEntriesEvent(FRED_GROUP).register(content -> {
			content.add(item);
		});

		Registry.register(Registries.ITEM, new Identifier(Fred.MOD_ID, name), item);
	}

	public static void registerModItems() {
		Fred.LOGGER.debug("Registering items from " + Fred.MOD_ID);
		registerItems();
	}
}