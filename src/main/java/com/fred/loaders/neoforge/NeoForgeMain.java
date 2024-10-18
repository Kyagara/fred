//? if neoforge {
/*package com.fred.loaders.neoforge;

import com.fred.Client;
import com.fred.Main;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.neoforged.fml.common.Mod;

@Mod(Main.MOD_ID)
public final class NeoForgeMain {
	public NeoForgeMain() {
		if (Platform.getEnvironment() == Env.CLIENT) {
			Client.init();
		}

		Main.init();
	}
}
*///?}
