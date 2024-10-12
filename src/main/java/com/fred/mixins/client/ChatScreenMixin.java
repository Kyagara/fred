package com.fred.mixins.client;

import com.fred.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {
	@Unique
	@Final
	private final MinecraftClient client = MinecraftClient.getInstance();

	@Inject(method = "onChatFieldUpdate", at = @At("HEAD"))
	private void onChatFieldUpdate(String chatText, CallbackInfo ci) {
		if (Main.CONFIG.enableChatTypingSound() && client != null && client.player != null) {
			client.player.playSound(SoundEvents.BLOCK_STONE_PLACE, Main.CONFIG.chatTypingVolume(), Main.CONFIG.chatTypingPitch());
		}
	}
}
