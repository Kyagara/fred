package net.kyagara.fred.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;

@Mixin(MusicTracker.class)
public abstract interface MusicTrackerAccessor {
    @Accessor("current")
    public SoundInstance getCurrent();
}