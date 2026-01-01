package land_here.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import land_here.mixin.accessor.EntityAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallFlyingMixin {

    @Unique
    private static final int GLIDING_FLAG_INDEX = 7;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void cancelGlideOnCrouch(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (!(self instanceof ServerPlayerEntity player)) return;

        boolean isFlying = ((EntityAccessor) self).callGetFlag(GLIDING_FLAG_INDEX);
        if (!isFlying) return;

        if (!player.isSneaking()) return;

        ((EntityAccessor) self).callSetFlag(GLIDING_FLAG_INDEX, false);
        player.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(player.getAbilities()));
    }
}
