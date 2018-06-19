package net.trentv.minecraft.darkness;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.minecraft.darkness.blocks.BlockLantern;
import net.trentv.minecraft.darkness.proxy.ProxyCommon;

@Mod(modid = AdvancedDarkness.MODID, name = AdvancedDarkness.NAME, version = AdvancedDarkness.VERSION, guiFactory = AdvancedDarkness.GUI_FACTORY, acceptedMinecraftVersions = "1.12")
public class AdvancedDarkness
{
	public static final String NAME = "Advanced Darkness";
	public static final String MODID = "advanced_darkness";
	public static final String VERSION = "2.0.0";
	public static final String GUI_FACTORY = "net.trentv.minecraft.darkness.ModGuiFactory";

	public static ModConfig config;

	@SidedProxy(clientSide = "net.trentv.minecraft.darkness.proxy.ProxyClient", serverSide = "net.trentv.minecraft.darkness.proxy.ProxyCommon", modId = AdvancedDarkness.MODID)
	public static ProxyCommon proxy;

	public static final BlockLantern BLOCK_LANTERN = (BlockLantern) new BlockLantern(Material.GLASS).setCreativeTab(CreativeTabs.DECORATIONS).setUnlocalizedName("lantern").setRegistryName(MODID, "lantern");
	public static final ItemBlock ITEMBLOCK_LANTERN = (ItemBlock) new ItemBlock(BLOCK_LANTERN).setRegistryName(BLOCK_LANTERN.getRegistryName());

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ModConfig();
		proxy.registerRenderers();
		proxy.registerEvents();
	}

	public static class ModConfig extends Configuration
	{
		public static final String CATEGORY = "advanced_darkness";
		public float lightLevel;
		public boolean autoSetLightLevel;
		public float maxLightLevel;

		public ModConfig()
		{
			super(new File("config/", AdvancedDarkness.MODID + ".cfg"));
		}

		public void init()
		{
			if (AdvancedDarkness.config == null)
			{
				AdvancedDarkness.config = new ModConfig();
				AdvancedDarkness.config.load();
			}
			lightLevel = getFloat("lightLevel", CATEGORY, -0.7f, -2500f, 2500f, "Mandated gamma level. This value cannot be changed after game launch.");
			autoSetLightLevel = getBoolean("autoSetlightLevel", CATEGORY, true, "Sets if the gamma is automatically set to lightLevel and cannot be changed.");
			maxLightLevel = getFloat("maxLightLevel", CATEGORY, 0.0f, -2500f, 2500f, "Maximum gamma level you can boost to using gamma adjuster blocks.");
			if (AdvancedDarkness.config.hasChanged())
			{
				AdvancedDarkness.config.save();
			}
		}

	}

	public static class GuiModConfig extends GuiConfig
	{
		public GuiModConfig(GuiScreen parent)
		{
			super(getParent(parent), getConfigElements(), AdvancedDarkness.MODID, false, false, AdvancedDarkness.NAME + " Config");
		}

		private static GuiScreen getParent(GuiScreen parent)
		{
			return parent;
		}

		private static List<IConfigElement> getConfigElements()
		{
			List<IConfigElement> configElements = new ArrayList<IConfigElement>();
			Configuration config = AdvancedDarkness.config;
			if (config != null)
			{
				ConfigCategory categoryClient = config.getCategory(ModConfig.CATEGORY);
				configElements.addAll(new ConfigElement(categoryClient).getChildElements());
			}
			return configElements;
		}
	}
}