package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.minecraft.client.toast.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ToastManager.class)
public abstract class ToastManagerMixin {
	@Inject(method = "add", at = @At("HEAD"), cancellable = true)
	public void add(Toast toast, CallbackInfo ci) {
		if (toast instanceof AdvancementToast && Fred.CONFIG.disableAdvancementToasts()) {
			ci.cancel();
		} else if (toast instanceof RecipeToast && Fred.CONFIG.disableRecipeToasts()) {
			ci.cancel();
		} else if (toast instanceof TutorialToast && Fred.CONFIG.disableTutorialToasts()) {
			ci.cancel();
		} else if (toast instanceof SystemToast && Fred.CONFIG.disableSystemToasts()) {
			ci.cancel();
		}
	}
}