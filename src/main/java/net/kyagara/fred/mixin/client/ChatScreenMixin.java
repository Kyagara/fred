package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {
	@Unique
	@Final
	private MinecraftClient client = MinecraftClient.getInstance();

	@Inject(method = "onChatFieldUpdate", at = @At("HEAD"))
	private void onChatFieldUpdate(String chatText, CallbackInfo ci) {
		if (Fred.CONFIG.enableChatTypingSound() && client != null && client.player != null) {
			client.player.playSound(SoundEvents.BLOCK_STONE_PLACE, SoundCategory.PLAYERS, Fred.CONFIG.chatTypingVolume(), Fred.CONFIG.chatTypingPitch());
		}
	}
}
