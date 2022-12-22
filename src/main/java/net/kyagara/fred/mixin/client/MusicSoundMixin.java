package net.kyagara.fred.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.sound.MusicSound;

@Environment(EnvType.CLIENT)
@Mixin(MusicSound.class)
public abstract class MusicSoundMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getMinDelay() {
        return FredConfig.musicMinDelay;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getMaxDelay() {
        return FredConfig.musicMaxDelay;
    }

}
