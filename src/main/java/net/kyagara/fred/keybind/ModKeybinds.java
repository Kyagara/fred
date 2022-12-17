package net.kyagara.fred.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kyagara.fred.Main;
import net.kyagara.fred.music.MusicControl;
import net.kyagara.fred.packets.ModPackets;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final KeyBinding PRINT_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_RIGHT_CONTROL, "key.fred.print_music");

    public static final KeyBinding SKIP_KEYBIND = register(GLFW.GLFW_KEY_RIGHT, "key.fred.skip_music");

    public static final KeyBinding INCREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_UP, "key.fred.increase_volume");

    public static final KeyBinding DECREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_DOWN, "key.fred.decrease_volume");

    public static final KeyBinding MY_MOVIE_SFX_KEYBIND = register(GLFW.GLFW_KEY_PERIOD, "key.fred.my_movie");

    public static KeyBinding register(int keybind, String name) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null) {
                return;
            }

            if (PRINT_MUSIC_KEYBIND.wasPressed()) {
                MusicControl.Print(client);
            }

            if (SKIP_KEYBIND.wasPressed()) {
                MusicControl.Skip(client);
            }

            if (INCREASE_VOLUME_KEYBIND.wasPressed()) {
                MusicControl.IncreaseVolume(client);
            }

            if (DECREASE_VOLUME_KEYBIND.wasPressed()) {
                MusicControl.DecreaseVolume(client);
            }

            if (MY_MOVIE_SFX_KEYBIND.wasPressed()) {
                ClientPlayNetworking.send(ModPackets.MY_MOVIE_SFX_PACKET, PacketByteBufs.create());
            }
        });

        return KeyBindingHelper
                .registerKeyBinding(new KeyBinding(name, InputUtil.Type.KEYSYM, keybind, "key.category.fred"));
    }

    public static void registerModKeybinds() {
        Main.LOGGER.debug("Registering keybinds from " + Main.MOD_ID);
    }
}