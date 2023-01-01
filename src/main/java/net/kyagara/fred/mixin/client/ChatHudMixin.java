package net.kyagara.fred.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "clear", at = @At("HEAD"), cancellable = true)
    public void clear(boolean clearHistory, CallbackInfo ci) {
        // If clearChat is set to false
        if (!FredConfig.clearChatOnLeave) {
            // Cancel this method, which will stop Minecraft from clearing the chat
            ci.cancel();
        }
    }

    @Inject(method = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"), cancellable = true)
    private void addMessage(Text message, @Nullable MessageSignatureData signature, int ticks,
            @Nullable MessageIndicator indicator, boolean refresh, CallbackInfo ci) {

        if (FredConfig.enableChatMessageSound) {
            this.client.player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS,
                    FredConfig.chatMessageVolume, FredConfig.chatMessagePitch);
        }
    }
}
