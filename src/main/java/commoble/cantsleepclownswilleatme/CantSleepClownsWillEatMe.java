package commoble.cantsleepclownswilleatme;

import java.util.List;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant_sleep_clowns_will_eat_me";
	
	private static CantSleepClownsWillEatMe instance;
	public static CantSleepClownsWillEatMe get() { return instance; }
	
	public final CommonConfig config;
	
	public CantSleepClownsWillEatMe() // invoked by forge due to @Mod
	{
		instance = this;
		
		this.config = ConfigHelper.register(MODID, ModConfig.Type.COMMON, CommonConfig::create);
		NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onCanPlayerSleep);
	}
	
	void onCanPlayerSleep(CanPlayerSleepEvent event)
	{
		if (event.getProblem() != BedSleepingProblem.NOT_SAFE)
			return;
		
		ServerPlayer serverPlayer = event.getEntity();
		ServerLevel serverLevel = serverPlayer.serverLevel();
		Vec3 vec3 = Vec3.atBottomCenterOf(event.getPos());
		double hRadius = 8.0;
		double yRadius = 5.0;
		List<Monster> monsters = serverLevel.getEntitiesOfClass(Monster.class,
			new AABB(vec3.x() - hRadius, vec3.y() - yRadius, vec3.z() - hRadius, vec3.x() + hRadius, vec3.y() + yRadius, vec3.z() + hRadius),
			monster -> monster.isPreventingPlayerRest(serverPlayer));
		
		if (monsters != null && !monsters.isEmpty() && this.config.highlightMobs().get())
		{
			int ticks = config.highlightDuration().get();
			for (Monster monster : monsters)
			{
				monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, ticks));
			}
		}
	}
}