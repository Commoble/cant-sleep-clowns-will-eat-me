package commoble.cantsleepclownswilleatme;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public record CommonConfig(BooleanValue highlightMobs, IntValue highlightDuration)
{
    public static CommonConfig create(ForgeConfigSpec.Builder builder)
    {
    	return new CommonConfig(
    		builder
	        	.comment("Whether highlighting of sleep-preventing monsters is enabled. Set this to false to disable the feature.")
	        	.define("highlight_mobs", () -> true),
	        builder
	        	.comment("How long the highlight effect will last, in ticks.")
	        	.defineInRange("highlight_duration", 60, 1, Integer.MAX_VALUE));
    }
}