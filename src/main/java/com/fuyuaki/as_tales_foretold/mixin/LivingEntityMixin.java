package com.fuyuaki.as_tales_foretold.mixin;


import com.fuyuaki.as_tales_foretold.api.Config;
import com.fuyuaki.as_tales_foretold.api.logic.HerobrineSystem;
import com.fuyuaki.as_tales_foretold.registries.ModTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    LivingEntity thisLiving = (LivingEntity)(Object)this;

    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void herobrine$PickUpLoot(ServerLevel p_level, DamageSource damageSource, CallbackInfo ci){
        if ( p_level.random.nextFloat() < (1.0F / Config.pickUpMobDrops) && !thisLiving.getType().is(ModTags.Entities.CANNOT_PICKUP)) {
            HerobrineSystem.enqueueSound(p_level,null,thisLiving.position(),SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS,0.6F,1.0F,true);
            HerobrineSystem.increasePlayerMoodiness(0.1F);
            ci.cancel();
        }
    }
}
