package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ChatMessageMixin {
    @Shadow
    private MinecraftClient client;

    @Inject(method = "onChatMessage", at = @At("TAIL"), cancellable = true)
    public void onChatMessage(ChatMessageS2CPacket packet, CallbackInfo ci) {
        if (FredConfig.chatMessageSound) {
            this.client.getSoundManager().play(new PositionedSoundInstance(SoundEvents.ITEM_FLINTANDSTEEL_USE.getId(),
                    SoundCategory.PLAYERS, FredConfig.chatMessageVolume, FredConfig.chatMessagePitch,
                    SoundInstance.createRandom(), false, 0, SoundInstance.AttenuationType.NONE, 0, 0, 0, true));
        }
    }
}
