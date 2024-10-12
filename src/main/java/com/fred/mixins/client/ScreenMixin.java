package com.fred.mixins.client;

import com.fred.Main;
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

@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	@Nullable
	protected MinecraftClient client;

	@Inject(method = "renderBackground*", at = @At("HEAD"), cancellable = true)
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (Main.CONFIG.disableInventoryBackground()) {
			if (client != null && client.currentScreen != null && client.currentScreen instanceof AbstractInventoryScreen) {
				ci.cancel();
			}
		}
	}
}