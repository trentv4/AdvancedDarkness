package net.trentv.minecraft.darkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.minecraft.darkness.AdvancedDarkness;
import net.trentv.minecraft.darkness.CommonProxy;
import net.trentv.minecraft.darkness.common.ADBlocks;
import net.trentv.minecraft.darkness.common.ADItems;

public class ClientProxy extends CommonProxy
{
	private ItemModelMesher registry;
	
	@Override
	public void registerRenderers()
	{
		registry = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		register(ADItems.uselessItem);
		register(ADBlocks.blockGammaAdjuster);
	}
	
	public void register(Item item)
	{
		registry.register(item, 0, new ModelResourceLocation(AdvancedDarkness.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	public void register(Block block)
	{
		registry.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(AdvancedDarkness.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}

	@Override
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new FMLClientEvents());
	}
}
