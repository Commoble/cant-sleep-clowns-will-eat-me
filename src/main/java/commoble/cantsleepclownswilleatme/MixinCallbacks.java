package commoble.cantsleepclownswilleatme;

import java.util.List;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public class MixinCallbacks
{
	/**
	 * Called from ServerPlayerMixin after nearby monsters are queried in ServerPlayer::startSleepInBed
	 * @param list The nearby monsters that would prevent the player from sleeping, if any
	 */
	public static void onStartSleepInBed(Player player, List<?> list)
	{
		CommonConfig config = CantSleepClownsWillEatMe.get().serverConfig;
		if (list != null && !list.isEmpty() && config.highlightMobs().get())
		{
			int ticks = config.highlightDuration().get();
			for (Object obj : list)
			{
				if (obj instanceof Monster monster)
				{
					monster.addEffect(new MobEffectInstance(MobEffects.GLOWING, ticks));
				}
			}
		}
	}
}
