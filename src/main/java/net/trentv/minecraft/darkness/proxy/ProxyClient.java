package net.trentv.minecraft.darkness.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.trentv.minecraft.darkness.AdvancedDarkness;

public class ProxyClient extends ProxyCommon
{

	@Override
	public void registerRenderers()
	{
		ModelLoader.setCustomModelResourceLocation(AdvancedDarkness.ITEMBLOCK_LANTERN, 0, new ModelResourceLocation(AdvancedDarkness.ITEMBLOCK_LANTERN.getRegistryName(), "inventory"));
	}

	@Override
	public void registerEvents()
	{
		super.registerEvents();
		MinecraftForge.EVENT_BUS.register(new ProxyClient());
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		int lightMod = 0;
		int lightRadius = 15;
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player != null)
		{
			World world = player.world;
			BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
			int count = 0;
			for (int i = -lightRadius; i <= lightRadius; i++)
			{
				for (int j = -lightRadius; j <= lightRadius; j++)
				{
					for (int k = -lightRadius; k < lightRadius; k++)
					{
						Block block = world.getBlockState(pos.add(i, j, k)).getBlock();
						if (block == AdvancedDarkness.BLOCK_LANTERN)
						{
							count++;
						}
					}
				}
			}
			lightMod = count;
		}

		if (true | AdvancedDarkness.config.autoSetLightLevel)
		{
			float lightLevel = AdvancedDarkness.config.lightLevel;
			float maxLightLevel = AdvancedDarkness.config.maxLightLevel;
			lightLevel += 0.1 * lightMod;
			if (lightLevel > maxLightLevel)
			{
				lightLevel = maxLightLevel;
			}
			Minecraft.getMinecraft().gameSettings.gammaSetting = lightLevel;
		}
	}

}