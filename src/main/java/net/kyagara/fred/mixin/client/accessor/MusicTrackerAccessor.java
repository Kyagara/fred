package net.kyagara.fred.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;

@Environment(EnvType.CLIENT)
@Mixin(MusicTracker.class)
public abstract interface MusicTrackerAccessor {
    @Accessor("current")
    public SoundInstance getCurrent();
}