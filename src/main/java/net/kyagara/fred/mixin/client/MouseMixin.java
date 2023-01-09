package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.keybind.SpyglassZoomKeybind;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
public abstract class MouseMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (client.player == null) {
            return;
        }

        if (client.options.getPerspective().isFirstPerson() && client.player.isUsingSpyglass()) {
            float newValue = MathHelper.clamp(SpyglassZoomKeybind.spyglassFOV - ((float) vertical * 0.1F), 0.1F, 0.8F);

            if (SpyglassZoomKeybind.spyglassFOV != newValue) {
                SpyglassZoomKeybind.spyglassFOV = newValue;

                client.player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F,
                        1.0F + SpyglassZoomKeybind.spyglassFOV);
            }

            ci.cancel();
        }
    }
}
