package net.kyagara.fred.mixin;

import net.kyagara.fred.keybind.ModKeybinds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicTracker.class)
public abstract class MusicTrackerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private SoundInstance current;

    @Inject(method = "play", at = @At("HEAD"))
    private void play(MusicSound type, CallbackInfo ci) {
        if (this.client.world != null) {
            current = PositionedSoundInstance.music(new SoundEvent(type.getSound().getId()));
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void changeMusic(CallbackInfo ci) {
        handlePauseKey();
        handlePrintMusicKey();
    }

    private void handlePauseKey() {
        if (ModKeybinds.pauseResume) {
            ModKeybinds.pauseResume = false;

            if (ModKeybinds.isPaused) {
                ModKeybinds.isPaused = false;

                this.client.getSoundManager().resumeAll();

                if (this.client.player != null) {
                    printOverlay("music.playing");
                }

                return;
            }

            ModKeybinds.isPaused = true;
            this.client.getSoundManager().pauseAll();

            if (this.client.player != null) {
                printOverlay("music.pause");
            }
        }
    }

    private void handlePrintMusicKey() {
        if (ModKeybinds.printMusic) {
            ModKeybinds.printMusic = false;

            if (this.current == null || this.client.world == null) {
                return;
            }

            printMusic();
        }
    }

    private void printMusic() {
        final Text music = Text.translatable(this.current.getSound().getIdentifier().toString());

        this.client.inGameHud.setOverlayMessage(Text.translatable("record.nowPlaying", music), false);
    }

    private void printOverlay(String identifier) {
        this.client.inGameHud.setOverlayMessage(Text.translatable(identifier), false);
    }
}
