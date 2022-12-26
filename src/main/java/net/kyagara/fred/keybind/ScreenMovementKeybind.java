package net.kyagara.fred.keybind;

import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ScreenMovementKeybind {
    private static long handle = -1;

    public static void CheckForMovementKeybind(MinecraftClient client) {
        if (handle == -1) {
            handle = client.getWindow().getHandle();
        }

        for (KeyBinding key : client.options.allKeys) {
            if (key.getCategory() == KeyBinding.MOVEMENT_CATEGORY) {
                key.setPressed(
                        InputUtil.isKeyPressed(handle, ((KeyBindingAccessor) key).fabric_getBoundKey().getCode()));
            }
        }
    }
}
