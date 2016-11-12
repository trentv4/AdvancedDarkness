package net.trentv.minecraft.darkness.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.minecraft.darkness.AdvancedDarkness;
import net.trentv.minecraft.darkness.CommonProxy;

public class ClientProxy extends CommonProxy
{
	private ItemModelMesher registry;
	
	@Override
	public void registerRenderers()
	{
		System.out.println("Model registered.");
		ItemModelMesher a = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		a.register(AdvancedDarkness.itemBlockGammaAdjuster, 0, new ModelResourceLocation(AdvancedDarkness.itemBlockGammaAdjuster.getRegistryName(), "inventory"));
	}
	
	@Override
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
}
