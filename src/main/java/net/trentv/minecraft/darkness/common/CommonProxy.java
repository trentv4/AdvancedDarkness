package net.trentv.minecraft.darkness.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.trentv.minecraft.darkness.AdvancedDarkness;
import net.trentv.minecraft.darkness.AdvancedDarknessConfiguration.ModConfig;

public class CommonProxy
{
	private int tickCount = 0;

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

	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		tickCount++;
		if (tickCount > AdvancedDarkness.config.darknessDamageDelay)
		{
			tickCount = 0;
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		EntityPlayer p = event.player;
		ModConfig config = AdvancedDarkness.config;

		if (p.world.isRemote || !config.isDarknessDamaging)
			return;

		if (p.world.getLight(p.getPosition(), false) <= 5)
		{
			if (tickCount == 0)
			{
				p.setEntityInvulnerable(false);
				p.hurtResistantTime = 0;
				p.attackEntityFrom(AdvancedDarkness.DAMAGE_DARKNESS, config.darknessDamage);
			}
		}
	}
}