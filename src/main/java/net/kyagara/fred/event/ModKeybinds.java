package net.kyagara.fred.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kyagara.fred.Main;
import net.kyagara.fred.sound.ModSounds;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final KeyBinding MY_MOVIE_KEYBIND = register(GLFW.GLFW_KEY_O, "key.category.fred",
            "key.fred.my_movie");

    public static KeyBinding register(int keybind, String category, String name) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (MY_MOVIE_KEYBIND.wasPressed()) {
                // server side not implemented - only you can hear it
                client.player.playSound(ModSounds.MY_MOVIE_SFX, 0.8F, 1F);
            }
        });

        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                name,
                InputUtil.Type.KEYSYM,
                keybind,
                category));
    }

    public static void registerModKeybinds() {
        Main.LOGGER.debug("Registering keybinds from " + Main.MOD_ID);
    }
}