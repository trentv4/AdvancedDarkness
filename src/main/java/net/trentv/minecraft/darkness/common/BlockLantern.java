package net.trentv.minecraft.darkness.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLantern extends Block
{
	public static final PropertyBool IS_ON_CEILING = PropertyBool.create("isonceiling");

	public BlockLantern(Material materialIn)
	{
		super(materialIn);
		setLightLevel(1f);
		setLightOpacity(0);
		setDefaultState(blockState.getBaseState().withProperty(IS_ON_CEILING, false));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		updateBlockState(worldIn, pos);
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
	{
		updateBlockState(worldIn, pos);
	}

	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, IS_ON_CEILING);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if (meta == 0)
		{
			return getDefaultState().withProperty(IS_ON_CEILING, true);
		}
		return getDefaultState().withProperty(IS_ON_CEILING, false);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		if (state.getValue(IS_ON_CEILING))
		{
			return 0;
		}
		return 1;
	}

	public void updateBlockState(World world, BlockPos pos)
	{
		boolean value = false;
		if (world.getBlockState(pos.up()).isNormalCube())
		{
			value = true;
		}
		world.setBlockState(pos, getDefaultState().withProperty(IS_ON_CEILING, value));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
}