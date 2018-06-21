package net.trentv.minecraft.darkness;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.minecraft.darkness.AdvancedDarknessConfiguration.ModConfig;
import net.trentv.minecraft.darkness.common.BlockLantern;
import net.trentv.minecraft.darkness.common.CommonProxy;

@Mod(modid = AdvancedDarkness.MODID, name = AdvancedDarkness.NAME, version = AdvancedDarkness.VERSION, guiFactory = AdvancedDarkness.GUI_FACTORY, acceptedMinecraftVersions = "1.12")
public class AdvancedDarkness
{
	public static final String NAME = "Advanced Darkness";
	public static final String MODID = "advanced_darkness";
	public static final String VERSION = "1.3";
	public static final String GUI_FACTORY = "net.trentv.minecraft.darkness.AdvancedDarknessConfiguration";

	public static ModConfig config;

	@SidedProxy(clientSide = "net.trentv.minecraft.darkness.client.ClientProxy", serverSide = "net.trentv.minecraft.darkness.common.CommonProxy", modId = AdvancedDarkness.MODID)
	public static CommonProxy proxy;

	public static final BlockLantern BLOCK_LANTERN = (BlockLantern) new BlockLantern(Material.GLASS).setCreativeTab(CreativeTabs.DECORATIONS).setUnlocalizedName("lantern").setRegistryName(MODID, "lantern");
	public static final ItemBlock ITEMBLOCK_LANTERN = (ItemBlock) new ItemBlock(BLOCK_LANTERN).setRegistryName(BLOCK_LANTERN.getRegistryName());

	public static final DamageSource DAMAGE_DARKNESS = new DamageSource("darkness").setDamageBypassesArmor();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ModConfig();
		proxy.registerRenderers();
		proxy.registerEvents();
	}
}