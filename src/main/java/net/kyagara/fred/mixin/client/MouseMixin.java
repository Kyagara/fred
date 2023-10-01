package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.keybind.ZoomKeybind;
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

@Environment(EnvType.CLIENT)
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
			if (client.player.isUsingSpyglass()) {
				float newValue = MathHelper.clamp(ZoomKeybind.spyglassFOV - ((float) vertical * 0.1F), 0.2F, 1.0F);

				if (ZoomKeybind.spyglassFOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F + ZoomKeybind.spyglassFOV);
					ZoomKeybind.spyglassFOV = newValue;
				}

				ci.cancel();
			}

			if (ZoomKeybind.isZooming) {
				double newValue = MathHelper.clamp(ZoomKeybind.FOV - vertical, 1.0, client.options.getFov().getValue());

				if (ZoomKeybind.FOV != newValue) {
					client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 2.0F);
					ZoomKeybind.FOV = newValue;
				}

				ci.cancel();
			}
		}
	}
}
