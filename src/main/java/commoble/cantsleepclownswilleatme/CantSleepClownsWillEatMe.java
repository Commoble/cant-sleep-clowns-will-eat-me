package commoble.cantsleepclownswilleatme;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant-sleep-clowns-will-eat-me";
	public static CantSleepClownsWillEatMe INSTANCE;
	
	public final ServerConfig serverConfig;
	
	public CantSleepClownsWillEatMe() // invoked by forge due to @Mod
	{
		INSTANCE = this;
		
		// mod bus has modloading init events and registry events
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		// forge bus is for server starting events and in-game events
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		
		this.serverConfig = ConfigHelper.register(ModConfig.Type.SERVER, ServerConfig::new);
		
	}
}