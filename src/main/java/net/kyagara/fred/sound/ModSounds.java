package net.kyagara.fred.sound;

import net.kyagara.fred.Fred;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent TRUMPET_USE = registerSound("item.trumpet.use");
    public static final SoundEvent THE_ROCK_BLOCK_SCARE = registerSound("block.the_rock_block");
    public static final SoundEvent REI_FUMO_QUASO = registerSound("block.rei_fumo_block");
    public static final SoundEvent MY_MOVIE_SFX = registerSound("sfx.my_movie");

    private static SoundEvent registerSound(String name) {
        Identifier identifier = new Identifier(Fred.MOD_ID, name);

        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    public static void registerModSounds() {
        Fred.LOGGER.debug("Registering sounds from " + Fred.MOD_ID);
    }
}