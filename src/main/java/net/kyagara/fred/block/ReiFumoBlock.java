package net.kyagara.fred.block;

import java.util.List;
import net.kyagara.fred.sound.ModSounds;
import net.kyagara.fred.stat.ModStatistics;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.state.property.Properties;

// Ideally this block should be something like an Item Frame,
// something that mounts on the wall.
public class ReiFumoBlock extends RedstoneBlock {
    private static final BooleanProperty POWERED = Properties.POWERED;

    public ReiFumoBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
    }

    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("block.fred.rei_fumo_block.tooltip").formatted(Formatting.AQUA));

        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return state.get(POWERED);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(POWERED)) {
            return 15;
        }

        return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockHitResult hit) {

        if (!world.isClient()) {
            world.setBlockState(pos, state.cycle(POWERED));

            if (!state.get(POWERED)) {
                world.playSound(null, pos, ModSounds.REI_FUMO_QUASO, SoundCategory.PLAYERS, 0.5F,
                        0.9F + world.random.nextFloat() * 0.15F);

                player.incrementStat(ModStatistics.QUASO_COUNT);
            }
        }

        return ActionResult.SUCCESS;
    }
}