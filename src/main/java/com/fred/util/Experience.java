package com.fred.util;

import com.fred.Configuration;
import net.minecraft.entity.player.PlayerEntity;

public class Experience {
	public static boolean canCreateXPBottle(PlayerEntity player) {
		if (player.isCreative() || Configuration.xpForXPBottle() <= 0) {
			return true;
		}

		int currentXP = (int) (getTotalExperienceForLevel(player.experienceLevel)
							   + player.experienceProgress * player.getNextLevelExperience());

		return currentXP >= Configuration.xpForXPBottle();
	}

	public static double getTotalExperienceForLevel(int level) {
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
