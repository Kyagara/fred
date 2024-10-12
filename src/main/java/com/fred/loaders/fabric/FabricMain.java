//? if fabric {
package com.fred.loaders.fabric;

import com.fred.Main;
import net.fabricmc.api.ModInitializer;

public class FabricMain implements ModInitializer {
	@Override
	public void onInitialize() {
		Main.init();
	}
}
//?}
