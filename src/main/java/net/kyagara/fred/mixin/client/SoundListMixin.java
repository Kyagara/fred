package net.kyagara.fred.mixin.client;

import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundEntry;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(targets = "net/minecraft/client/sound/SoundManager$SoundList")
public class SoundListMixin {
	@Shadow
	@Final
	Map<Identifier, WeightedSoundSet> loadedSounds;

	@Inject(method = "register", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void register(Identifier identifier, SoundEntry entry, ResourceManager resourceManager, CallbackInfo ci) {
		for (Sound sound : entry.getSounds()) {
			final Identifier soundIdentifier = sound.getIdentifier();

			if (sound.getRegistrationType() == Sound.RegistrationType.FILE && SoundManager.isSoundResourcePresent(sound, identifier, resourceManager) && (soundIdentifier.getPath().contains("music") || soundIdentifier.getPath().contains("records"))) {
				WeightedSoundSet newWeightedSoundSet = new WeightedSoundSet(identifier, entry.getSubtitle());

				loadedSounds.put(soundIdentifier, newWeightedSoundSet);
				newWeightedSoundSet.add(sound);
			}
		}
	}
}
