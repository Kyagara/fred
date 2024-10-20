package com.fred.mixins.client;

import com.fred.Client;
import com.fred.Configuration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
	private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
		if (client.player == null) {
			return;
		}

		if (client.options.getPerspective().isFirstPerson()) {
			if (Configuration.enableChangingSpyglassFOV() && client.player.isUsingSpyglass()) {
				float newValue = MathHelper.clamp(Client.SPYGLASS_FOV - ((float) vertical * 0.1F), 0.2F, 1.0F);

				if (Client.SPYGLASS_FOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F + Client.SPYGLASS_FOV);
					Client.SPYGLASS_FOV = newValue;
				}

				ci.cancel();
			}

			if (Client.IS_ZOOMING) {
				double newValue = MathHelper.clamp(Client.FOV - ((float) vertical * 0.1F), 0.2F, 1.0F);

				if (Client.FOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 2.0F);
					Client.FOV = newValue;
				}

				ci.cancel();
			}
		}
	}
}
