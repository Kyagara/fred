package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.keybind.ModKeybinds;
import net.kyagara.fred.keybind.ScreenMovementKeybind;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    @Final
    protected MinecraftClient client;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void tickMovement(CallbackInfo ci) {
        if (client.currentScreen instanceof HandledScreen) {
            if (ModKeybinds.isAutoWalking) {
                client.options.forwardKey.setPressed(true);
            }

            ScreenMovementKeybind.CheckForMovementKeybind(client);
        }

        if (client.world != null) {
            if (client.options.forwardKey.wasPressed()) {
                ModKeybinds.isAutoWalking = false;
            }

            if (ModKeybinds.isAutoWalking) {
                client.options.forwardKey.setPressed(true);
            }
        }
    }
}
