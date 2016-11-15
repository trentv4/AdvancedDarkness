package net.trentv.minecraft.darkness;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.minecraft.darkness.common.BlockLantern;
import net.trentv.minecraft.darkness.config.AdvancedDarknessConfigurations;

@Mod(modid = AdvancedDarkness.MODID, name = "Advanced Darkness", version = AdvancedDarkness.VERSION, acceptedMinecraftVersions = "[" + AdvancedDarkness.TARGET_VERSION + "]")
public class AdvancedDarkness
{
	public static final String MODID = "advanced_darkness";
	public static final String VERSION = "1.2";
	public static final String TARGET_VERSION = "1.10.2";

	public static AdvancedDarknessConfigurations config;
	
	@SidedProxy(clientSide = "net.trentv.minecraft.darkness.client.ClientProxy",
	            serverSide = "net.trentv.minecraft.darkness.server.ServerProxy", modId = AdvancedDarkness.MODID)
	public static CommonProxy proxy;
	public static BlockLantern blockLantern = (BlockLantern) new BlockLantern(Material.GLASS)
	                                                      .setCreativeTab(CreativeTabs.DECORATIONS)
	                                                      .setUnlocalizedName("lantern")
	                                                      .setRegistryName(MODID, "lantern");
	public static ItemBlock itemBlockLantern = (ItemBlock) new ItemBlock(blockLantern)
	                                                      .setRegistryName(MODID, "lantern");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new AdvancedDarknessConfigurations(event.getSuggestedConfigurationFile());
		GameRegistry.register(blockLantern);
		GameRegistry.register(itemBlockLantern);
		GameRegistry.addShapedRecipe(new ItemStack(itemBlockLantern, 1),
		                             " F ",
		                             "FGF",
		                             " I ", 'F', Blocks.IRON_BARS, 'G', Items.GLOWSTONE_DUST, 'I', Items.IRON_INGOT);
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
