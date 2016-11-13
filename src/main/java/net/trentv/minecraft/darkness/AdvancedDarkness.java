package net.trentv.minecraft.darkness;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.minecraft.darkness.common.BlockGammaAdjuster;
import net.trentv.minecraft.darkness.config.AdvancedDarknessConfigurations;

@Mod(modid = AdvancedDarkness.MODID, name = "Advanced Darkness", version = AdvancedDarkness.VERSION, acceptedMinecraftVersions = "[" + AdvancedDarkness.TARGET_VERSION + "]")
public class AdvancedDarkness
{
	public static final String MODID = "advanced_darkness";
	public static final String VERSION = "1.1";
	public static final String TARGET_VERSION = "1.10.2";

	public static AdvancedDarknessConfigurations config;
	
	@SidedProxy(clientSide = "net.trentv.minecraft.darkness.client.ClientProxy",
	            serverSide = "net.trentv.minecraft.darkness.server.ServerProxy", modId = AdvancedDarkness.MODID)
	public static CommonProxy proxy;
	public static BlockGammaAdjuster blockGammaAdjuster = (BlockGammaAdjuster) new BlockGammaAdjuster(Material.GLASS)
	                                                      .setCreativeTab(CreativeTabs.DECORATIONS)
	                                                      .setUnlocalizedName("gamma_adjuster")
	                                                      .setRegistryName(MODID, "gamma_adjuster");
	public static ItemBlock itemBlockGammaAdjuster = (ItemBlock) new ItemBlock(blockGammaAdjuster)
	                                                      .setRegistryName(MODID, "gamma_adjuster");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new AdvancedDarknessConfigurations(event.getSuggestedConfigurationFile());
		GameRegistry.register(blockGammaAdjuster);
		GameRegistry.register(itemBlockGammaAdjuster);
		proxy.registerRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerEvents();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
