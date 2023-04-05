package net.kyagara.fred.keybind;

import net.kyagara.fred.interfaces.GameOptionsInterface;
import net.kyagara.fred.mixin.client.accessor.MusicTrackerAccessor;
import net.kyagara.fred.mixin.client.accessor.SoundSetAccessor;
import net.kyagara.fred.screen.MusicPlayerScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundContainer;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;

public class MusicControlKeybind {
	private static boolean muted = false;

	public static void PlayMusic(MinecraftClient client, Identifier song) {
		if (song.getPath().equals("meta:missing_sound")) {
			client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);
			return;
		}

		MusicTracker tracker = client.getMusicTracker();

		tracker.stop();
		tracker.play(new MusicSound(RegistryEntry.of(SoundEvent.of(song)), 0, 0, true));

		Print(client);
	}

	public static void Print(MinecraftClient client) {
		SoundInstance current = ((MusicTrackerAccessor) client.getMusicTracker()).getCurrent();

		if (muted || current == null || current.getSound() == null || current.getId().getPath().equals("meta:missing_sound")) {
			client.inGameHud.setOverlayMessage(Text.translatable("music.fred.no_music"), false);
			return;
		}

		Text music = Text.translatable(current.getSound().getIdentifier().toString());

		client.inGameHud.setOverlayMessage(Text.translatable("music.fred.now_playing", music), false);
	}

	public static void ShowMusicPlayerScreen(MinecraftClient client) {
		if (client.player == null) {
			return;
		}

		List<Identifier> songs = new ArrayList<>();
		List<Identifier> discs = new ArrayList<>();
		List<Identifier> categories = new ArrayList<>();

		Random random = client.player.getRandom();

		for (Identifier key : client.getSoundManager().getKeys()) {
			if (client.getSoundManager().get(key) != null) {
				String path = key.getPath();

				if (!path.contains("music")) {
					continue;
				}

				if (path.contains("music_disc")) {
					songs.add(key);
					discs.add(key);
					continue;
				}

				if (path.contains("music.") && !categories.contains(key)) {
					categories.add(key);
				}

				SoundSetAccessor soundSet = ((SoundSetAccessor) client.getSoundManager().get(key));

				if (soundSet == null) {
					return;
				}

				List<SoundContainer<Sound>> sounds = soundSet.getSounds();

				for (SoundContainer<Sound> soundContainer : sounds) {
					Identifier song = soundContainer.getSound(random).getIdentifier();

					if (songs.contains(song)) {
						continue;
					}

					songs.add(song);
				}
			}
		}

		client.setScreen(new MusicPlayerScreen(songs, categories, discs));
	}

	public static void Skip(MinecraftClient client) {
		MusicTracker tracker = client.getMusicTracker();
		SoundInstance current = ((MusicTrackerAccessor) tracker).getCurrent();

		if (current == null || current.getSound() == null) {
			tracker.play(client.getMusicType());

			current = ((MusicTrackerAccessor) tracker).getCurrent();

			String song = current.getSound().getIdentifier().toString();

			if (song.equals("meta:missing_sound")) {
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

		((GameOptionsInterface) client.options).setSoundCategoryVolume(SoundCategory.MUSIC, volume);
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

		((GameOptionsInterface) client.options).setSoundCategoryVolume(SoundCategory.MUSIC, volume);
		client.options.write();

		if (volume == 0.00F) {
			muted = true;
		}

		client.inGameHud.setOverlayMessage(Text.translatable("music.fred.volume", Math.round(100.0F * volume)), false);
	}
}
