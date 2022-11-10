package net.kyagara.fred.items;

import java.util.List;
import net.kyagara.fred.sound.ModSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.entity.EntityPredicates;

public class TrumpetItem extends Item {
    public TrumpetItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fred.trumpet.tooltip").formatted(Formatting.RED));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);

        player.getItemCooldownManager().set(this, 25);

        player.playSound(ModSounds.TRUMPET_USE, 1.0F, 0.9F + world.random.nextFloat() * 0.2F);

        // Push entities
        if (!world.isClient) {
            List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class,
                    player.getBoundingBox().expand(5.0D), EntityPredicates.VALID_ENTITY);

            for (LivingEntity spookedEntity : entities) {
                if (spookedEntity == player)
                    continue;

                double deltaX = spookedEntity.getX() - player.getX() + world.random.nextDouble()
                        - world.random.nextDouble();

                double deltaZ = spookedEntity.getZ() - player.getZ() + world.random.nextDouble()
                        - world.random.nextDouble();

                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

                spookedEntity.velocityModified = true;

                spookedEntity.addVelocity(deltaX / (10.0D + distance), 5.0D / (10.0D + distance),
                        deltaZ / (10.0D + distance));
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}