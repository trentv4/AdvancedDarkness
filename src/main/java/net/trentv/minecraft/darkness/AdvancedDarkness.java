package net.trentv.minecraft.darkness;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.minecraft.darkness.client.FMLClientEvents;
import net.trentv.minecraft.darkness.config.AdvancedDarknessConfigurations;

@Mod(modid = AdvancedDarkness.MODID, name = "Advanced Darkness", version = AdvancedDarkness.VERSION, acceptedMinecraftVersions = "[" + AdvancedDarkness.TARGET_VERSION + "]")
public class AdvancedDarkness
{
	public static final String MODID = "advanced-darkness";
	public static final String VERSION = "1.1";
	public static final String TARGET_VERSION = "1.10.2";

	public static AdvancedDarknessConfigurations config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new AdvancedDarknessConfigurations(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new FMLClientEvents());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
