package net.kyagara.fred.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.util.ExperienceUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(GlassBottleItem.class)
public abstract class XPBottleItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity player, Hand hand,
            CallbackInfoReturnable<TypedActionResult<ItemStack>> ci) {

        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && FredConfig.enableXPBottleMechanic) {
            if (player.isSneaking() && ExperienceUtil.CanCreateXPBottle(player)) {
                player.addExperience(-FredConfig.xpForXPBottle);

                stack.decrement(1);
                player.giveItemStack(new ItemStack(Items.EXPERIENCE_BOTTLE, 1));
            }
        }
    }
}