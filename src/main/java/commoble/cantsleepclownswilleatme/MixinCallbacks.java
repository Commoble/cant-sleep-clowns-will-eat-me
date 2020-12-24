package commoble.cantsleepclownswilleatme;

import java.util.List;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MixinCallbacks
{
	/**
	 * Called from ServerPlayerEntityMixin after nearby monsters are queried in ServerPlayerEntity::trySleep
	 * @param list The nearby monsters that would prevent the player from sleeping, if any
	 */
	public static void onTrySleep(List<?> list)
	{
		if (list != null && !list.isEmpty() && CantSleepClownsWillEatMe.INSTANCE.serverConfig.highlightMobs.get())
		{
			int ticks = CantSleepClownsWillEatMe.INSTANCE.serverConfig.highlightDuration.get();
			for (Object obj : list)
			{
				if (obj instanceof MonsterEntity)
				{
					((MonsterEntity)obj).addPotionEffect(new EffectInstance(Effects.GLOWING, ticks));
				}
			}
		}
	}
}
