package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	@Nullable
	protected MinecraftClient client;

	@Inject(method = "renderBackground*", at = @At("HEAD"), cancellable = true)
	public void renderBackground(DrawContext context, CallbackInfo ci) {
		if (Fred.CONFIG.disableInventoryBackground()) {
			if (client != null && client.currentScreen != null && client.currentScreen instanceof AbstractInventoryScreen) {
				ci.cancel();
			}
		}
	}
}