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
import net.trentv.minecraft.darkness.common.ADBlocks;

@SideOnly(Side.CLIENT)
public class FMLClientEvents
{
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		int lightMod = 0;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(player != null)
		{
			World world = player.worldObj;
			lightMod = checkLightModifiers(world, (int) player.posX, (int) player.posY, (int) player.posZ, 10);
		}
		 
		if (AdvancedDarkness.config.autoSetLightLevel)
		{
			float lightLevel = AdvancedDarkness.config.lightLevel;
			lightLevel += 0.1 * lightMod;
			Minecraft.getMinecraft().gameSettings.gammaSetting = lightLevel;
		}
	}
	
	private int checkLightModifiers(World worldObj, int x, int y, int z, int radius)
	{
		int count = 0;
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k < radius; k++)
				{
					Block block = worldObj.getBlockState(new BlockPos(x + i, y + j, z + k)).getBlock();
					
					if(block == ADBlocks.blockGammaAdjuster)
					{
						count++;
					}
				}
			}
		}
		return count;
	}
}
