package commoble.cantsleepclownswilleatme.mixins;

import java.util.List;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.datafixers.util.Either;

import commoble.cantsleepclownswilleatme.MixinCallbacks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin
{
	@Inject(method="startSleepInBed", locals=LocalCapture.CAPTURE_FAILEXCEPTION, at = @At(value="INVOKE_ASSIGN", target="net/minecraft/world/level/Level.getEntitiesOfClass (Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;",ordinal=0))
	private void cantSleepClownsWillEatMe_ServerPlayerMixin_startSleepInBed_invokeAssign_getEntitiesOfClass(BlockPos at, CallbackInfoReturnable<Either> cir, Optional optAt, Player.BedSleepingProblem ret, Direction direction, double d0, double d1, Vec3 vec3, List list)
	{
		// have the mixin be a static method call to minimize disruptions to the bytecode
		// mixin doesn't like generics so the list has to be a mystery list
		MixinCallbacks.onStartSleepInBed((Player)(Object)this, list);
	}
}
