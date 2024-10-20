package com.fred.mixins.client;

import com.fred.Client;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
	private void getFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> ci) {
		if (Client.IS_ZOOMING) {
			ci.setReturnValue(Client.FOV);
		}
	}
}
