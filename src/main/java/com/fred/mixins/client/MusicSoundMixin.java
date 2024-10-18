package com.fred.mixins.client;

import com.fred.Configuration;
import net.minecraft.sound.MusicSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MusicSound.class)
public abstract class MusicSoundMixin {
	@Inject(method = "getMinDelay", at = @At("HEAD"), cancellable = true)
	public void getMinDelay(CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(Configuration.musicMinDelay());
	}

	@Inject(method = "getMaxDelay", at = @At("HEAD"), cancellable = true)
	public void getMaxDelay(CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(Configuration.musicMaxDelay());
	}
}
