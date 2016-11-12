package net.trentv.minecraft.darkness.client;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.common.MinecraftForge;
import net.trentv.minecraft.darkness.CommonProxy;

public class ClientProxy extends CommonProxy
{
	private ItemModelMesher registry;
	
	@Override
	public void registerRenderers()
	{

	}
	
	@Override
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new FMLClientEvents());
	}
}
