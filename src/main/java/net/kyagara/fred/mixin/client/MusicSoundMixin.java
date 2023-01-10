package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.minecraft.sound.MusicSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(MusicSound.class)
public abstract class MusicSoundMixin {
    @Inject(method = "getMinDelay", at = @At("HEAD"), cancellable = true)
    public void getMinDelay(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(Fred.CONFIG.musicMinDelay());
    }

    @Inject(method = "getMaxDelay", at = @At("HEAD"), cancellable = true)
    public void getMaxDelay(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(Fred.CONFIG.musicMaxDelay());
    }
}
