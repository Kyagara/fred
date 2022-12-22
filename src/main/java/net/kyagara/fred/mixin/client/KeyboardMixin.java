package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.hook.ClientEventsHooks;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private boolean repeatEvents;

    private long handle = -1;

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;wrapScreenError(Ljava/lang/Runnable;Ljava/lang/String;Ljava/lang/String;)V", ordinal = 0), cancellable = true)
    public void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        // TODO: fix keybind being unpressed when opening any screen
        // TODO: maybe make this a class method
        if (FredConfig.inventoryMovement && this.client.currentScreen instanceof HandledScreen) {
            for (KeyBinding key : this.client.options.allKeys) {
                if (key.getCategory() == KeyBinding.MOVEMENT_CATEGORY && key.matchesKey(keyCode, scanCode)) {
                    if (this.handle == -1) {
                        this.handle = this.client.getWindow().getHandle();
                    }

                    key.setPressed(InputUtil.isKeyPressed(this.handle, keyCode));
                }
            }
        }

        if (action == 1 && (action != 2 || !this.repeatEvents)) {
            ClientEventsHooks.KEY_PRESSED.invoker()
                    .keyPressed(this.client, keyCode, scanCode, modifiers);
        }
    }
}
