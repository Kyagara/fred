package com.fred.mixins.client;

import com.fred.Client;
import com.fred.Configuration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
	@Unique
	private static final MinecraftClient client = MinecraftClient.getInstance();

	@Inject(method = "getFovMultiplier", at = @At("HEAD"), cancellable = true)
	public void getFovMultiplier(CallbackInfoReturnable<Float> ci) {
		if (Configuration.enableChangingSpyglassFOV() && client.player != null && client.options.getPerspective().isFirstPerson() && client.player.isUsingSpyglass()) {
			ci.setReturnValue(Client.SPYGLASS_FOV);
		}
	}
}
