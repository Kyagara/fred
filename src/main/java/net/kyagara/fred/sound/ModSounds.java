package net.kyagara.fred.sound;

import net.kyagara.fred.Fred;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
	public static final SoundEvent TRUMPET_USE = registerSound("item.trumpet.use");
	public static final SoundEvent THE_ROCK_BLOCK_SCARE = registerSound("block.the_rock_block");

	private static SoundEvent registerSound(String name) {
		Identifier identifier = new Identifier(Fred.MOD_ID, name);

		return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
	}

	public static void registerModSounds() {
		Fred.LOGGER.debug("Registering sounds from " + Fred.MOD_ID);
	}
}