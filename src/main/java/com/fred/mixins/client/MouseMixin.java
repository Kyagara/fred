package com.fred.mixins.client;

import com.fred.Configuration;
import com.fred.keybinds.Zoom;
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
				float newValue = MathHelper.clamp(Zoom.spyglassFOV - ((float) vertical * 0.1F), 0.2F, 1.0F);

				if (Zoom.spyglassFOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F + Zoom.spyglassFOV);
					Zoom.spyglassFOV = newValue;
				}

				ci.cancel();
			}

			if (Zoom.isZooming) {
				double newValue = MathHelper.clamp(Zoom.FOV - vertical, 1.0, client.options.getFov().getValue());

				if (Zoom.FOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 2.0F);
					Zoom.FOV = newValue;
				}

				ci.cancel();
			}
		}
	}
}
