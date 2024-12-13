package com.fuyuaki.as_tales_foretold.api.logic.data_storage;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public record EnqueuedSound(
        @NotNull Level level,
        @Nullable Player player,
        @NotNull Vec3 pos,
        @NotNull SoundEvent sound,
        @NotNull SoundSource category,
        float volume,
        float pitch,
        boolean shouldOffset
) {

}
