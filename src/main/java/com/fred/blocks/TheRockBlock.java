package com.fred.blocks;

import com.fred.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TheRockBlock extends RedstoneBlock {
	private static final BooleanProperty POWERED = Properties.POWERED;

	public TheRockBlock(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(POWERED, false));
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

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient()) {
			world.setBlockState(pos, state.cycle(POWERED));

			if (!state.get(POWERED)) {
				world.playSound(null, pos, Main.THE_ROCK_BLOCK_SCARE, SoundCategory.PLAYERS, 0.7F, 1F);
				player.incrementStat(Main.ROCK_COUNT);
			}
		}

		return ActionResult.SUCCESS;
	}
}
