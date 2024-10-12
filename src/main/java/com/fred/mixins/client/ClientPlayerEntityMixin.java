package com.fred.mixins.client;

import com.fred.Client;
import com.fred.Main;
import com.fred.keybinds.ScreenMovement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
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
		if (Main.CONFIG.enableInventoryMovement() && client.currentScreen instanceof InventoryScreen) {
			ScreenMovement.CheckForMovementKeybind(client);
		}

		if (Client.isAutoWalking) {
			client.options.forwardKey.setPressed(true);
		}
	}
}
