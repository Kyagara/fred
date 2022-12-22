package net.kyagara.fred.keybind;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class ScreenMovementKeybind {
    private static long handle = -1;

    public static void CheckForMovementKeybind(MinecraftClient client, int keyCode, int scanCode) {
        if (handle == -1) {
            handle = client.getWindow().getHandle();
        }

        for (KeyBinding key : client.options.allKeys) {
            if (key.getCategory() == KeyBinding.MOVEMENT_CATEGORY && key.matchesKey(keyCode, scanCode)) {

                key.setPressed(InputUtil.isKeyPressed(handle, keyCode));
            }
        }
    }
}
