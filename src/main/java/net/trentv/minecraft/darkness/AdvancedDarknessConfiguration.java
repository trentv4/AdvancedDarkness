package net.trentv.minecraft.darkness;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class AdvancedDarknessConfiguration implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance)
	{
	}

	@Override
	public boolean hasConfigGui()
	{
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen)
	{
		return new GuiModConfig(parentScreen);
	}

	@Nullable
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	public static class ModConfig extends Configuration
	{
		public static final String CAT_DARKNESS = "core";
		public static final String CAT_DIMENSIONS = "dimensions";
		public static final String CAT_DAMAGE = "damage";

		public float lightLevel;
		public boolean autoSetLightLevel;
		public float maxLightLevel;
		public ArrayList<ResourceLocation> lightIncreasingBlocks;

		public ArrayList<Integer> dimensionsWhitelist;
		public ArrayList<Integer> dimensionsBlacklist;
		public boolean isDarkByDefault;

		public boolean isDarknessDamaging;
		public float darknessDamage;
		public int darknessDamageDelay;

		public ModConfig()
		{
			super(new File("config/", AdvancedDarkness.MODID + ".cfg"));
			load();
			update();
		}

		public void update()
		{
			lightLevel = getFloat("lightLevel", CAT_DARKNESS, -0.7f, -2500f, 2500f, "Mandated gamma level.");
			autoSetLightLevel = getBoolean("autoSetlightLevel", CAT_DARKNESS, true, "Sets if the gamma is automatically set to lightLevel and cannot be changed.");
			maxLightLevel = getFloat("maxLightLevel", CAT_DARKNESS, 0.0f, -2500f, 2500f, "Maximum gamma level you can boost to using gamma adjuster blocks.");
			String[] tempLightIncreasingBlocks = getStringList("lightIncreasingBlocks", CAT_DARKNESS, new String[] { "advanced_darkness:lantern" }, "List of blocks that increase the light level");
			lightIncreasingBlocks = new ArrayList<ResourceLocation>();
			for (String s : tempLightIncreasingBlocks)
			{
				lightIncreasingBlocks.add(new ResourceLocation(s));
			}

			isDarkByDefault = getBoolean("isDarkByDefault", CAT_DIMENSIONS, true, "Is every dimension dark by default");
			String[] tempWhitelist = getStringList("darknessWhitelist", CAT_DIMENSIONS, new String[] {}, "What dimensions have darkness force applied");
			dimensionsWhitelist = new ArrayList<Integer>();
			for (String s : tempWhitelist)
			{
				dimensionsWhitelist.add(Integer.parseInt(s));
			}

			String[] tempBlacklist = getStringList("dimensionsBlacklist", CAT_DIMENSIONS, new String[] {}, "What dimensions have darkness force never applied");
			dimensionsBlacklist = new ArrayList<Integer>();
			for (String s : tempBlacklist)
			{
				dimensionsBlacklist.add(Integer.parseInt(s));
			}

			isDarknessDamaging = getBoolean("isDarknessDamaging", CAT_DAMAGE, false, "Does darkness damage the player");
			darknessDamage = getFloat("darknessDamage", CAT_DAMAGE, 4f, 0f, Float.MAX_VALUE, "Damage per tick in hearts.");
			darknessDamageDelay = getInt("darknessDamageDelay", CAT_DAMAGE, 40, 0, Integer.MAX_VALUE, "Time between damage ticks in game ticks");

			save();
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
				ConfigCategory categoryClient = config.getCategory(ModConfig.CAT_DARKNESS);
				configElements.addAll(new ConfigElement(categoryClient).getChildElements());
				categoryClient = config.getCategory(ModConfig.CAT_DIMENSIONS);
				configElements.addAll(new ConfigElement(categoryClient).getChildElements());
				categoryClient = config.getCategory(ModConfig.CAT_DAMAGE);
				configElements.addAll(new ConfigElement(categoryClient).getChildElements());
			}
			return configElements;
		}
	}
}