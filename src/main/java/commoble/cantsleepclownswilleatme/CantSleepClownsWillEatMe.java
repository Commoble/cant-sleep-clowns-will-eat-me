package commoble.cantsleepclownswilleatme;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant-sleep-clowns-will-eat-me";
	public static CantSleepClownsWillEatMe INSTANCE;
	
	public final ServerConfig serverConfig;
	
	public CantSleepClownsWillEatMe() // invoked by forge due to @Mod
	{
		INSTANCE = this;
		
		this.serverConfig = ConfigHelper.register(ModConfig.Type.SERVER, ServerConfig::new);
		
	}
}