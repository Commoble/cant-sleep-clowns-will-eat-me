package commoble.cantsleepclownswilleatme;

import commoble.cantsleepclownswilleatme.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig
{
    public final ConfigValueListener<Boolean> highlightMobs;
    public final ConfigValueListener<Integer> highlightDuration;
    
    public ServerConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
    {
        this.highlightMobs = subscriber.subscribe(builder
        	.comment("Whether highlighting of sleep-preventing monsters is enabled. Set this to false to disable the feature.")
        	.define("highlight_mobs", true));
        this.highlightDuration = subscriber.subscribe(builder
        	.comment("How long the highlight effect will last, in ticks.")
        	.define("highlight_duration", 60));
    }
}