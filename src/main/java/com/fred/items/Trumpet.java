package com.fred.items;

import com.fred.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class Trumpet extends Item {
	public Trumpet(Settings settings) {
		super(settings);
		settings.rarity(Rarity.RARE);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		tooltip.add(Text.translatable("item.fred.trumpet.tooltip").formatted(Formatting.RED));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		player.getItemCooldownManager().set(this, 25);

		// Pushing entities - from trumpet-skeleton-fabric
		if (!world.isClient) {
			world.playSoundFromEntity(null, player, Main.TRUMPET_USE.get(), SoundCategory.PLAYERS, 0.8F, 0.9F + world.random.nextFloat() * 0.2F);

			List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(5.0D), EntityPredicates.VALID_ENTITY);

			for (LivingEntity spookedEntity : entities) {
				if (spookedEntity == player) {
					continue;
				}

				double deltaX = spookedEntity.getX() - player.getX() + world.random.nextDouble() - world.random.nextDouble();
				double deltaZ = spookedEntity.getZ() - player.getZ() + world.random.nextDouble() - world.random.nextDouble();
				double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

				spookedEntity.velocityModified = true;
				spookedEntity.addVelocity(deltaX / (10.0D + distance), 5.0D / (10.0D + distance), deltaZ / (10.0D + distance));
			}

			player.incrementStat(Main.DOOT_COUNT.get());
		}

		return TypedActionResult.pass(player.getStackInHand(hand));
	}
}