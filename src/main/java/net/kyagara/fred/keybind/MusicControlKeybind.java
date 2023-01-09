package net.kyagara.fred.keybind;

import net.kyagara.fred.mixin.client.accessor.MusicTrackerAccessor;
import net.kyagara.fred.screen.MusicPlayerScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class MusicControlKeybind {
	private static boolean muted = false;

	public static void PlayMusic(MinecraftClient client, Identifier song) {
		if (client == null) {
			return;
		}

		if (song.toString().equals("meta:missing_sound")) {
			client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);
			return;
		}

		MusicTracker tracker = client.getMusicTracker();

		tracker.stop();
		tracker.play(new MusicSound(new SoundEvent(song), 0, 0, true));

		Print(client);
	}

	public static void Print(MinecraftClient client) {
		SoundInstance current = ((MusicTrackerAccessor) client.getMusicTracker()).getCurrent();

		if (muted || current == null || current.getSound() == null || current.getId().toString().equals("meta:missing_sound")) {
			client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);
			return;
		}

		Text music = Text.translatable(current.getSound().getIdentifier().toString());
		client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);
	}

	public static void ShowMusicPlayerScreen(MinecraftClient client) {
		ArrayList<Identifier> musics = new ArrayList<>();
		ArrayList<Identifier> discs = new ArrayList<>();

		for (Identifier key : client.getSoundManager().getKeys()) {
			if (key.getPath().contains("music")) {
				if (key.getPath().contains("music_disc")) {
					discs.add(key);
					continue;
				}

				if (key.toString().equals("meta:missing_sound")) {
					continue;
				}

				musics.add(key);
			}
		}

		musics.addAll(discs);
		client.setScreen(new MusicPlayerScreen(musics));
	}

	public static void Skip(MinecraftClient client) {
		MusicTracker tracker = client.getMusicTracker();
		SoundInstance current = ((MusicTrackerAccessor) tracker).getCurrent();

		if (current == null || current.getSound() == null) {
			tracker.play(client.getMusicType());

			current = ((MusicTrackerAccessor) tracker).getCurrent();

			String song = current.getSound().getIdentifier().toString();

			if (song.startsWith("meta:")) {
				client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);
				return;
			}

			Text music = Text.translatable(song);
			client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);

			return;
		}

		tracker.stop();
		tracker.play(client.getMusicType());

		current = ((MusicTrackerAccessor) tracker).getCurrent();

		Text music = Text.translatable(current.getSound().getIdentifier().toString());
		client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);
	}

	public static void IncreaseVolume(MinecraftClient client) {
		float volume = Math.min(client.options.getSoundVolume(SoundCategory.MUSIC) + 0.05F, 1.0F);

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
		float volume = Math.max(client.options.getSoundVolume(SoundCategory.MUSIC) - 0.05F, 0.0F);

		client.options.setSoundVolume(SoundCategory.MUSIC, volume);
		client.options.write();

		if (volume == 0.00F) {
			muted = true;
		}

		client.inGameHud.setOverlayMessage(Text.translatable("music.fred.volume", Math.round(100.0F * volume)), false);
	}
}
