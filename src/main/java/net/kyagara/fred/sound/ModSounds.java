package net.kyagara.fred.sound;

import net.kyagara.fred.Main;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent TRUMPET_USE = registerSoundEvent("item.trumpet.use");
    public static final SoundEvent MY_MOVIE_SFX = registerSoundEvent("sfx.my_movie");
    public static final SoundEvent THE_ROCK_BLOCK_SCARE = registerSoundEvent("block.the_rock_block");

    private static SoundEvent registerSoundEvent(String id) {
        final Identifier identifier = new Identifier(Main.MOD_ID, id);

        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    public static void registerModSounds() {
        Main.LOGGER.debug("Registering sounds from " + Main.MOD_ID);
    }
}