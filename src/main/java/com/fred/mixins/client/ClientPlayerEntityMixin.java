package com.fred.mixins.client;

import com.fred.Client;
import com.fred.Configuration;
import com.fred.mixins.client.accessor.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
	@Shadow
	@Final
	protected MinecraftClient client;

	@Unique
	private static final long handle = MinecraftClient.getInstance().getWindow().getHandle();

	@Inject(method = "tickMovement", at = @At("HEAD"))
	public void tickMovement(CallbackInfo ci) {
		if (Configuration.enableInventoryMovement() && client.currentScreen instanceof InventoryScreen) {
			for (KeyBinding key : client.options.allKeys) {
				if (key.getCategory().equals(KeyBinding.MOVEMENT_CATEGORY)) {
					key.setPressed(InputUtil.isKeyPressed(handle, ((KeyBindingAccessor) key).getBoundKey().getCode()));
				}
			}
		}

		if (Client.IS_AUTO_WALKING) {
			client.options.forwardKey.setPressed(true);
		}
	}
}
