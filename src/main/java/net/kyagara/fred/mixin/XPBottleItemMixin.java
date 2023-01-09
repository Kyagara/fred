package net.kyagara.fred.mixin;

import net.kyagara.fred.Fred;
import net.kyagara.fred.util.ExperienceUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public abstract class XPBottleItemMixin {
	@Inject(method = "use", at = @At("HEAD"))
	public void use(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> ci) {
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient && Fred.CONFIG.enableXPBottleMechanic()) {
			if (player.isSneaking() && ExperienceUtil.CanCreateXPBottle(player)) {
				player.addExperience(-Fred.CONFIG.xpForXPBottle());

				stack.decrement(1);
				player.giveItemStack(new ItemStack(Items.EXPERIENCE_BOTTLE, 1));
			}
		}
	}
}