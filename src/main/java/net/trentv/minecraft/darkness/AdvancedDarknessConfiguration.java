package net.trentv.minecraft.darkness;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
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
		public static final String CATEGORY = "advanced_darkness";
		public float lightLevel;
		public boolean autoSetLightLevel;
		public float maxLightLevel;

		public ModConfig()
		{
			super(new File("config/", AdvancedDarkness.MODID + ".cfg"));
			load();
			update();
		}

		public void update()
		{
			lightLevel = getFloat("lightLevel", CATEGORY, -0.7f, -2500f, 2500f, "Mandated gamma level. This value cannot be changed after game launch.");
			autoSetLightLevel = getBoolean("autoSetlightLevel", CATEGORY, true, "Sets if the gamma is automatically set to lightLevel and cannot be changed.");
			maxLightLevel = getFloat("maxLightLevel", CATEGORY, 0.0f, -2500f, 2500f, "Maximum gamma level you can boost to using gamma adjuster blocks.");
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
				ConfigCategory categoryClient = config.getCategory(ModConfig.CATEGORY);
				configElements.addAll(new ConfigElement(categoryClient).getChildElements());
			}
			return configElements;
		}
	}
}