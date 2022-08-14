package commoble.cantsleepclownswilleatme;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant_sleep_clowns_will_eat_me";
	
	private static CantSleepClownsWillEatMe instance;
	public static CantSleepClownsWillEatMe get() { return instance; }
	
	public final CommonConfig serverConfig;
	
	public CantSleepClownsWillEatMe() // invoked by forge due to @Mod
	{
		instance = this;
		
		this.serverConfig = ConfigHelper.register(ModConfig.Type.COMMON, CommonConfig::create);
		
	}
}