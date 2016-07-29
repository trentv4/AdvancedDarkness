package net.trentv.minecraft.darkness.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.trentv.minecraft.darkness.AdvancedDarkness;

public class FMLClientEvents 
{
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
/*		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(player == null) return;
		World world = player.worldObj;
		if(world == null) return;*/
		if(AdvancedDarkness.config.autoSetLightLevel)
		{
			Minecraft.getMinecraft().gameSettings.gammaSetting = AdvancedDarkness.config.lightLevel;
		}
	}
}
