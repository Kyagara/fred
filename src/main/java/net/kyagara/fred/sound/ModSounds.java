package net.kyagara.fred.sound;

import net.kyagara.fred.Main;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent TRUMPET_USE = registerSoundEvent("item.trumpet.use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Main.MOD_ID, name);

        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerModSounds() {
        Main.LOGGER.debug("Registering sounds from " + Main.MOD_ID);
    }
}