package com.fred.mixins;

import com.fred.Main;
import com.fred.util.Experience;
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
		if (!Main.CONFIG.enableXPBottleMechanic()) {
			return;
		}

		if (!world.isClient && Experience.CanCreateXPBottle(player) && player.isSneaking()) {
			ItemStack stack = player.getStackInHand(hand);

			player.addExperience(-Main.CONFIG.xpForXPBottle());

			stack.decrement(1);
			player.giveItemStack(new ItemStack(Items.EXPERIENCE_BOTTLE, 1));
		}
	}
}
