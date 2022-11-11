package net.kyagara.fred.sound;

import net.kyagara.fred.Main;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent TRUMPET_USE = registerSoundEvent(new Identifier(Main.MOD_ID, "item.trumpet.use"));

    public static final SoundEvent MY_MOVIE_SFX = registerSoundEvent(new Identifier(Main.MOD_ID, "sfx.my_movie"));

    private static SoundEvent registerSoundEvent(Identifier identifier) {
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    public static void registerModSounds() {
        Main.LOGGER.debug("Registering sounds from " + Main.MOD_ID);
    }
}