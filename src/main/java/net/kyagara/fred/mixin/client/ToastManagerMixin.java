package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.RecipeToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.toast.TutorialToast;
import net.minecraft.client.toast.SystemToast;

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