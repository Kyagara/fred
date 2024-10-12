//? if fabric {
package com.fred.loaders.fabric;

import com.fred.Client;
import net.fabricmc.api.ClientModInitializer;

public class FabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Client.init();
	}
}
//?}
