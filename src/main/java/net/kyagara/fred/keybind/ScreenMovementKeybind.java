package net.kyagara.fred.keybind;

import net.kyagara.fred.mixin.client.accessor.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ScreenMovementKeybind {
    private static final long handle = MinecraftClient.getInstance().getWindow().getHandle();

    public static void CheckForMovementKeybind(MinecraftClient client) {
        for (KeyBinding key : client.options.allKeys) {
            if (key.getCategory() == KeyBinding.MOVEMENT_CATEGORY) {
                key.setPressed(
                        InputUtil.isKeyPressed(handle, ((KeyBindingAccessor) key).getBoundKey().getCode()));

                if (key.equals(client.options.forwardKey) && key.isPressed() && ModKeybinds.isAutoWalking) {
                    ModKeybinds.isAutoWalking = false;
                }
            }
        }
    }
}
