package net.kyagara.fred.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(GlassBottleItem.class)
public class XPBottleItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity player, Hand hand,
            CallbackInfoReturnable<TypedActionResult<ItemStack>> ci) {

        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && FredConfig.enableXPBottle) {
            if (player.isSneaking() && canCreateXPBottle(player)) {
                player.addExperience(-FredConfig.xpForXPBottle);

                stack.decrement(1);
                player.giveItemStack(new ItemStack(Items.EXPERIENCE_BOTTLE, 1));
            }
        }
    }

    private static boolean canCreateXPBottle(PlayerEntity player) {
        if (player.isCreative() || FredConfig.xpForXPBottle <= 0) {
            return true;
        }

        int currentXP = (int) (getExperienceForLevel(player.experienceLevel)
                + player.experienceProgress * player.getNextLevelExperience());

        return currentXP >= FredConfig.xpForXPBottle;
    }

    private static double getExperienceForLevel(int level) {
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