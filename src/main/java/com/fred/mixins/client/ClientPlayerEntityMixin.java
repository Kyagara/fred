package com.fred.mixins.client;

import com.fred.Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
	@Shadow
	@Final
	protected MinecraftClient client;

	@Inject(method = "tickMovement", at = @At("HEAD"))
	public void tickMovement(CallbackInfo ci) {
		if (client.currentScreen instanceof HandledScreen && !(client.currentScreen instanceof AnvilScreen)) {
			if (Client.isAutoWalking) {
				client.options.forwardKey.setPressed(true);
			}
		}

		if (client.world != null) {
			if (client.options.forwardKey.wasPressed()) {
				Client.isAutoWalking = false;
			}

			if (Client.isAutoWalking) {
				client.options.forwardKey.setPressed(true);
			}
		}
	}
}
