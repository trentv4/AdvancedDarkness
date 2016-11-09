package net.trentv.minecraft.darkness.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class AdvancedDarknessConfigurations extends Configuration
{
	public float lightLevel;
	public boolean autoSetLightLevel;

	public AdvancedDarknessConfigurations(File file)
	{
		super(file);
		this.load();
		lightLevel = this.getFloat("lightLevel", "advanced_darkness", -0.7f, -2500f, 2500f, "Mandated gamma level. This value cannot be changed after game launch.");
		autoSetLightLevel = this.getBoolean("autoSetlightLevel", "advanced_darkness", true, "Sets if the gamma is automatically set to lightLevel and cannot be changed.");
		this.save();
	}
}
