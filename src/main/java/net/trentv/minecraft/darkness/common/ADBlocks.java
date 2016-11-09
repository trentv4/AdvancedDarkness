package net.trentv.minecraft.darkness.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.minecraft.darkness.AdvancedDarkness;

public class ADBlocks
{
	public static Block blockGammaAdjuster = new BlockGammaAdjuster(Material.ROCK).setUnlocalizedName("gamma-adjuster").setHardness(2.0f);
	
	public static void registerBlocks()
	{
		register(blockGammaAdjuster);
	}
	
	private static void register(Block block)
	{
		GameRegistry.register(new ItemBlock(block).setRegistryName(AdvancedDarkness.MODID, block.getUnlocalizedName().substring(5)));
		GameRegistry.register(block.setRegistryName(AdvancedDarkness.MODID, block.getUnlocalizedName().substring(5)));
	}
}
