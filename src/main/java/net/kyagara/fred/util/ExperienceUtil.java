package net.kyagara.fred.util;

import net.kyagara.fred.Fred;
import net.minecraft.entity.player.PlayerEntity;

public class ExperienceUtil {
	public static boolean CanCreateXPBottle(PlayerEntity player) {
		if (player.isCreative() || Fred.CONFIG.xpForXPBottle() <= 0) {
			return true;
		}

		int currentXP = (int) (GetTotalExperienceForLevel(player.experienceLevel)
				+ player.experienceProgress * player.getNextLevelExperience());

		return currentXP >= Fred.CONFIG.xpForXPBottle();
	}

	public static double GetTotalExperienceForLevel(int level) {
		if (level == 0) {
			return 0;
		}

		if (level <= 16) {
			return Math.pow(level, 2) + 6 * level;
		}

		if (level <= 31) {
			return 2.5 * Math.pow(level, 2) - 40.5 * level + 360;
		}

		return 4.5 * Math.pow(level, 2) - 162.5 * level + 2220;
	}
}
