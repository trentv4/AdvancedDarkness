package net.trentv.minecraft.darkness.common;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.trentv.minecraft.darkness.AdvancedDarkness;

public class CommonProxy
{
	public void registerRenderers()
	{
	}

	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(CommonProxy.class);
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(AdvancedDarkness.BLOCK_LANTERN);
		ForgeRegistries.ITEMS.register(AdvancedDarkness.ITEMBLOCK_LANTERN);
	}

	@SubscribeEvent
	public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e)
	{
		if (e.getModID().equals(AdvancedDarkness.MODID))
		{
			AdvancedDarkness.config.update();
		}
	}
}