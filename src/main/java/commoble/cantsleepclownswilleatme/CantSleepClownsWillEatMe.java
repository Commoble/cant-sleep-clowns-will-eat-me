package commoble.cantsleepclownswilleatme;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant-sleep-clowns-will-eat-me";
	public static CantSleepClownsWillEatMe INSTANCE;
	
	public final ServerConfig serverConfig;
	
	public CantSleepClownsWillEatMe() // invoked by forge due to @Mod
	{
		INSTANCE = this;
		
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
			() -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a,b) -> true));
		
		this.serverConfig = ConfigHelper.register(ModConfig.Type.SERVER, ServerConfig::new);
		
	}
}