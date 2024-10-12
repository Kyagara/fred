package com.fred.mixins.client.accessor;

import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;
import net.minecraft.client.sound.WeightedSoundSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(WeightedSoundSet.class)
public interface SoundSetAccessor {
	@Accessor
	List<SoundContainer<Sound>> getSounds();
}