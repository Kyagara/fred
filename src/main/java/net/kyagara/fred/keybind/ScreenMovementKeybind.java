package net.kyagara.fred.keybind;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ScreenMovementKeybind {
    private static long handle = -1;

    public static void CheckForMovementKeybind(MinecraftClient client) {
        if (handle == -1) {
            handle = client.getWindow().getHandle();
        }

        // TODO: get the actual input being pressed, currently this only loops movement
        // keys and check if any of them are being pressed, this works well however this
        // loops only takes into account default movement keybinds
        for (KeyBinding key : client.options.allKeys) {
            if (key.getCategory() == KeyBinding.MOVEMENT_CATEGORY) {
                key.setPressed(InputUtil.isKeyPressed(handle, key.getDefaultKey().getCode()));
            }
        }
    }
}
