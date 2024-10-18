package com.fred.mixins.client;

import com.fred.Configuration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "clear", at = @At("HEAD"), cancellable = true)
	public void clear(boolean clearHistory, CallbackInfo ci) {
		// If clearChat is set to false
		if (!Configuration.clearChatOnLeave()) {
			// Cancel this method, which will stop Minecraft from clearing the chat
			ci.cancel();
		}
	}

	@Inject(method = "addMessage(Lnet/minecraft/client/gui/hud/ChatHudLine;)V", at = @At("HEAD"))
	private void addMessage(ChatHudLine message, CallbackInfo ci) {
		if (Configuration.enableChatMessageSound() && client.player != null) {
			client.player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, Configuration.chatMessageVolume(), Configuration.chatMessagePitch());
		}
	}
}
