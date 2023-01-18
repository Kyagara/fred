package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.interfaces.GameOptionsInterface;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public abstract class GameOptionsMixin implements GameOptionsInterface {
	@Shadow
	@Final
	private Map<SoundCategory, SimpleOption<Double>> soundVolumeLevels;

	@Override
	public void setSoundCategoryVolume(SoundCategory soundCategory, double volume) {
		SimpleOption<Double> simpleOption = soundVolumeLevels.get(soundCategory);

		if (simpleOption != null) {
			simpleOption.setValue(volume);
		}
	}
}