package commoble.cantsleepclownswilleatme;

import java.util.List;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;

@Mod(CantSleepClownsWillEatMe.MODID) // tells forge to construct this during modloading
public class CantSleepClownsWillEatMe
{
	public static final String MODID = "cant_sleep_clowns_will_eat_me";
	
	public static final CommonConfig CONFIG = Util.make(() -> {
		final var mod = ModList.get().getModContainerById(MODID).get();
		final org.apache.commons.lang3.tuple.Pair<CommonConfig, ModConfigSpec> entry = new ModConfigSpec.Builder().configure(CommonConfig::create);
		final CommonConfig config = entry.getLeft();
		final ModConfigSpec spec = entry.getRight();
		mod.registerConfig(ModConfig.Type.COMMON, spec);
		return config;
	});
	
	public CantSleepClownsWillEatMe() // invoked by norge due to @Mod
	{
		NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onCanPlayerSleep);
	}
	
	void onCanPlayerSleep(CanPlayerSleepEvent event)
	{
		if (event.getProblem() != BedSleepingProblem.NOT_SAFE)
			return;
		
		ServerPlayer serverPlayer = event.getEntity();
		ServerLevel serverLevel = serverPlayer.level();
		Vec3 vec3 = Vec3.atBottomCenterOf(event.getPos());
		double hRadius = 8.0;
		double yRadius = 5.0;
		List<Monster> monsters = serverLevel.getEntitiesOfClass(Monster.class,
			new AABB(vec3.x() - hRadius, vec3.y() - yRadius, vec3.z() - hRadius, vec3.x() + hRadius, vec3.y() + yRadius, vec3.z() + hRadius),
			monster -> monster.isPreventingPlayerRest(serverLevel, serverPlayer));
		
		if (!monsters.isEmpty() && CONFIG.highlightMobs().get())
		{
			int ticks = CONFIG.highlightDuration().get();
			for (Monster monster : monsters)
			{
				monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, ticks));
			}
		}
	}
	
	public static record CommonConfig(BooleanValue highlightMobs, IntValue highlightDuration)
	{
	    public static CommonConfig create(ModConfigSpec.Builder builder)
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
}