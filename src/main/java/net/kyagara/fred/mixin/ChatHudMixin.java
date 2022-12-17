package net.kyagara.fred.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.client.gui.hud.ChatHud;

@Environment(EnvType.CLIENT)
@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    @Shadow
    public abstract void reset();

    @Inject(method = "clear", at = @At("HEAD"), cancellable = true)
    public void clear(boolean clearHistory, CallbackInfo ci) {
        // If clearChat is set to false
        if (!FredConfig.clearChat) {
            // Cancel this method, which will stop Minecraft from clearing the chat
            ci.cancel();
        }
    }
}
