package net.kyagara.fred.item;

import java.util.List;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stat.ModStatistics;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;

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
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.GOLD_NUGGET);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.getItemCooldownManager().set(this, 25);

        // Pushing entities - from trumpet-skeleton-fabric
        if (!world.isClient) {
            world.playSoundFromEntity(null, player, ModSounds.TRUMPET_USE, SoundCategory.PLAYERS, 0.8F,
                    0.9F + world.random.nextFloat() * 0.2F);

            List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class,
                    player.getBoundingBox().expand(5.0D), EntityPredicates.VALID_ENTITY);

            for (LivingEntity spookedEntity : entities) {
                if (spookedEntity == player) {
                    continue;
                }

                double deltaX = spookedEntity.getX() - player.getX() + world.random.nextDouble()
                        - world.random.nextDouble();

                double deltaZ = spookedEntity.getZ() - player.getZ() + world.random.nextDouble()
                        - world.random.nextDouble();

                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

                spookedEntity.velocityModified = true;

                spookedEntity.addVelocity(deltaX / (10.0D + distance), 5.0D / (10.0D + distance),
                        deltaZ / (10.0D + distance));
            }

            player.getStackInHand(hand).damage(1, player, p -> p.sendToolBreakStatus(hand));

            player.incrementStat(ModStatistics.DOOT_COUNT);
        }

        return super.use(world, player, hand);
    }
}