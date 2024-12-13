package com.fuyuaki.as_tales_foretold.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AmbientSoundHandler;
import net.minecraft.client.resources.sounds.BiomeAmbientSoundsHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeAmbientSoundsHandler.class)
public abstract class BiomeAmbientSoundsHandlerMixin {

    @Shadow @Final public LocalPlayer player;
    @Unique
    BiomeAmbientSoundsHandler handler = (BiomeAmbientSoundsHandler)(Object)this;


    @Inject(method = "tick",at = @At("HEAD"))
    private void mixinCaveNoises(CallbackInfo ci){
        handler.moodSettings.ifPresent(settings -> {
            if (handler.moodSettings.get() == AmbientMoodSettings.LEGACY_CAVE_SETTINGS) {
                Level level = handler.player.level();
                int i = settings.getBlockSearchExtent() * 2 + 1;
                BlockPos blockpos = BlockPos.containing(
                        handler.player.getX() + (double) handler.random.nextInt(i) - (double) settings.getBlockSearchExtent(),
                        handler.player.getEyeY() + (double) handler.random.nextInt(i) - (double) settings.getBlockSearchExtent(),
                        handler.player.getZ() + (double) handler.random.nextInt(i) - (double) settings.getBlockSearchExtent()
                );
                int j = level.getBrightness(LightLayer.SKY, blockpos);
                if (j > 0) {
                    handler.moodiness += (float) j / 30.0F * 0.001F;
                } else if (level.getBrightness(LightLayer.BLOCK, blockpos) < 12) {
                    handler.moodiness = handler.moodiness - (float) (level.getBrightness(LightLayer.BLOCK, blockpos) - 8) / (settings.getTickDelay() * 1.2F);
                }
            }
        });
    }
}
