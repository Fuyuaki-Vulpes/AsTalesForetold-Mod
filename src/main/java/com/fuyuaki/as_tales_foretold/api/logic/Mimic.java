package com.fuyuaki.as_tales_foretold.api.logic;

import com.fuyuaki.as_tales_foretold.api.Config;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Mimic {
    private static List<SoundEvent> validSounds = List.of(
            SoundEvents.CREEPER_PRIMED, SoundEvents.AMBIENT_CAVE.value(), SoundEvents.PLAYER_TELEPORT, SoundEvents.PLAYER_ATTACK_SWEEP,
            SoundEvents.ENDER_PEARL_THROW, SoundEvents.TNT_PRIMED,SoundEvents.GHAST_HURT, SoundEvents.GRASS_PLACE

    );

    private static int tickCount = 0;


    public static void tick(Player player) {
        addTickCount(1);

        if (tickCount == Config.mimicryTimer){

            setTickCount(0);

            if (player.getRandom().nextDouble() < Config.mimicryRate){

                playMimicry(player);


            }
        }
    }

    private static void playMimicry(Player player) {
        SoundEvent event = validSounds.get(player.getRandom().nextInt(validSounds.size()));
        Vec3 viewVector = player.getViewVector(0);
        Vec3 direction = viewVector.reverse().yRot(player.getRandom().nextIntBetweenInclusive(-60, 60))
                .multiply(
                        player.getRandom().nextDouble() * 2,
                        player.getRandom().nextDouble(),
                        player.getRandom().nextDouble() * 2);
        Vec3 pos = player.position().add(direction);
        player.level().playSound(player, pos.x,pos.y,pos.z, event, SoundSource.MASTER, 0.6F, 1.0F);

    }

    public static void setTickCount(int tickCount) {
        Mimic.tickCount = tickCount;
    }
    public static void addTickCount(int tickCount){
        Mimic.tickCount = Mimic.tickCount + tickCount;
    }
}
