package net.trentv.minecraft.darkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.minecraft.darkness.AdvancedDarkness;

@SideOnly(Side.CLIENT)
public class ClientEvents
{
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		int lightMod = 0;
		int lightRadius = 15;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(player != null)
		{
			World world = player.worldObj;
			lightMod = checkLightModifiers(world, new BlockPos(player.posX, player.posY, player.posZ), lightRadius);
		}
		 
		if (AdvancedDarkness.config.autoSetLightLevel)
		{
			float lightLevel = AdvancedDarkness.config.lightLevel;
			lightLevel += 0.1 * lightMod;
			if(lightLevel > 0) lightLevel = 0;
			Minecraft.getMinecraft().gameSettings.gammaSetting = lightLevel;
		}
	}
	
	private int checkLightModifiers(World worldObj, BlockPos pos, int radius)
	{
		int count = 0;
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k < radius; k++)
				{
					Block block = worldObj.getBlockState(pos.add(i, j, k)).getBlock();
					
					if(block == AdvancedDarkness.blockGammaAdjuster)
					{
						count++;
					}
				}
			}
		}
		return count;
	}
}
