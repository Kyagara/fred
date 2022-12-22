package net.kyagara.fred.sound;

import net.kyagara.fred.mixin.client.accessor.MusicTrackerAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;

public class MusicControl {
    private static boolean muted = false;

    public static void Print(MinecraftClient client) {
        SoundInstance current = ((MusicTrackerAccessor) client.getMusicTracker()).getCurrent();

        if (muted || current == null || current.getSound() == null) {
            client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);

            return;
        }

        Text music = Text.translatable(current.getSound().getIdentifier().toString());

        client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);
    }

    public static void Skip(MinecraftClient client) {
        MusicTracker tracker = client.getMusicTracker();
        SoundInstance current = ((MusicTrackerAccessor) tracker).getCurrent();

        if (current == null || current.getSound() == null) {
            tracker.play(client.getMusicType());

            current = ((MusicTrackerAccessor) tracker).getCurrent();

            Text music = Text.translatable(current.getSound().getIdentifier().toString());
            client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);

            return;
        }

        client.getSoundManager().stop(current);
        tracker.play(client.getMusicType());

        current = ((MusicTrackerAccessor) tracker).getCurrent();

        Text music = Text.translatable(current.getSound().getIdentifier().toString());
        client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);
    }

    public static void IncreaseVolume(MinecraftClient client) {
        float volume = client.options.getSoundVolume(SoundCategory.MUSIC);

        volume = Math.min(volume + 0.05F, 1.0F);

        client.options.setSoundVolume(SoundCategory.MUSIC, volume);
        client.options.write();

        // Start playing music after unmuting.
        if (muted) {
            client.getMusicTracker().play(client.getMusicType());
            muted = false;
        }

        client.inGameHud.setOverlayMessage(Text.translatable("music.fred.volume", Math.round(100.0F * volume)), false);
    }

    public static void DecreaseVolume(MinecraftClient client) {
        float volume = client.options.getSoundVolume(SoundCategory.MUSIC);
        volume = Math.max(volume - 0.05F, 0.0F);

        client.options.setSoundVolume(SoundCategory.MUSIC, volume);
        client.options.write();

        if (volume == 0.00F) {
            muted = true;
        }

        client.inGameHud.setOverlayMessage(Text.translatable("music.fred.volume", Math.round(100.0F * volume)), false);
    }
}
