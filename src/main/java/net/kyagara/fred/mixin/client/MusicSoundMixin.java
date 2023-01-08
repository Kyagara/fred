package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.sound.MusicSound;

@Environment(EnvType.CLIENT)
@Mixin(MusicSound.class)
public abstract class MusicSoundMixin {
    @Inject(method = "getMinDelay", at = @At("HEAD"), cancellable = true)
    public void getMinDelay(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(FredConfig.musicMinDelay);
    }

    @Inject(method = "getMaxDelay", at = @At("HEAD"), cancellable = true)
    public void getMaxDelay(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(FredConfig.musicMaxDelay);
    }
}
