package net.kyagara.fred.block;

import java.util.List;

import net.kyagara.fred.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

public class TheRockBlock extends Block {
    public TheRockBlock(Settings settings) {
        super(settings);

        this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(SCARE, false));
    }

    private static final BooleanProperty SCARE = BooleanProperty.of("powered");

    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("block.fred.the_rock_block.tooltip").formatted(Formatting.AQUA));

        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return state.get(SCARE);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (state.get(SCARE)) {
            return 32;
        }

        return 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if (!world.isClient()) {
            world.setBlockState(pos, state.cycle(SCARE));

            if (state.get(SCARE) == false) {
                world.playSound(null, pos, ModSounds.THE_ROCK_BLOCK_SCARE, SoundCategory.PLAYERS, 0.7F, 1F);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SCARE);
    }
}