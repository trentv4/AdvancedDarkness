package net.trentv.minecraft.darkness.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.minecraft.darkness.AdvancedDarkness;

public class ADItems
{
	public static Item uselessItem = new Item().setUnlocalizedName("useless");
	
	public static void registerItems()
	{
		register(uselessItem);
	}
	
	private static void register(Item item)
	{
		item.setRegistryName(AdvancedDarkness.MODID, item.getUnlocalizedName().substring(5));
		GameRegistry.register(item);
	}
}
