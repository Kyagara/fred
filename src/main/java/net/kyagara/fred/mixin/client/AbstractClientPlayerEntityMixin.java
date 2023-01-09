package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.kyagara.fred.keybind.SpyglassZoomKeybind;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	@Inject(method = "getFovMultiplier", at = @At("HEAD"), cancellable = true)
	public void getFovMultiplier(CallbackInfoReturnable<Float> ci) {
		if (client.player != null && Fred.CONFIG.enableChangingSpyglassFOV() && client.options.getPerspective().isFirstPerson() && client.player.isUsingSpyglass()) {
			ci.setReturnValue(SpyglassZoomKeybind.spyglassFOV);
		}
	}
}
