package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.keybind.SpyglassZoomKeybind;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "getFovMultiplier", at = @At("INVOKE"), cancellable = true)
    public void getFovMultiplier(CallbackInfoReturnable<Float> ci) {
        if (FredConfig.enableChangingSpyglassFOV
                && client.options.getPerspective().isFirstPerson()
                && client.player.isUsingSpyglass()) {

            ci.setReturnValue(SpyglassZoomKeybind.spyglassFOV);
        }
    }
}
