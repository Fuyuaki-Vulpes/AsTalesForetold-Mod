package com.fuyuaki.as_tales_foretold.api.logic;

import com.fuyuaki.as_tales_foretold.api.logic.data_storage.EnqueuedSound;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AmbientSoundHandler;
import net.minecraft.client.resources.sounds.BiomeAmbientSoundsHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction8;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class HerobrineSystem {
    static boolean loggedIn = false;
    public static final Random RANDOM = new Random();
    public static final Minecraft MINECRAFT = Minecraft.getInstance();

    private static final List<EnqueuedSound> enqueuedSounds = new ArrayList<>();
    private static int validTick = 0;
    private static int validTickConfirmation = 20;

    public static void tickClient(){

        if (validTick == validTickConfirmation){
            enqueuedSounds.forEach(SS ->  playSound(SS.level(),SS.player(),SS.pos(),SS.sound(),SS.category(),SS.volume(),SS.pitch(),SS.shouldOffset()));
            enqueuedSounds.clear();
            setValidTick(0);
            setValidTickConfirmation(RANDOM.nextInt(32));
        }
        else setValidTick(validTick + 1);
    }

    private static void setValidTickConfirmation(int i) {
        HerobrineSystem.validTickConfirmation = i;
    }

    public static void setValidTick(int validTick) {
        HerobrineSystem.validTick = validTick;
    }


    public static void tickServer(Level level){
        if (RANDOM.nextDouble() < 0.001 && RANDOM.nextBoolean() && RANDOM.nextFloat() < 0.01){
            connectionUpdated(level);
        }
    }

    private static void playSound(Level level, Player player, Vec3 pos, SoundEvent sound, SoundSource category, float volume, float pitch, boolean shouldOffset) {
        double xOffset = level.getRandom().nextDouble() * 2 - 1;
        double zOffset = level.getRandom().nextFloat() * 2 - 1;
        if (shouldOffset) {

            level.playSound(
                    player, (double) pos.x + xOffset, (double) pos.y + 0.5, (double) pos.z + zOffset, sound, category, volume, pitch
            );
        } else {

            level.playSound(
                    player, (double) pos.x, (double) pos.y + 0.5, (double) pos.z, sound, category, volume, pitch
            );
        }

    }

    public static void increasePlayerMoodiness(float percentage){
        if (MINECRAFT.player == null) return;

        for (AmbientSoundHandler ambientsoundhandler : MINECRAFT.player.ambientSoundHandlers) {
            if (ambientsoundhandler instanceof BiomeAmbientSoundsHandler) {
                ((BiomeAmbientSoundsHandler) ambientsoundhandler).moodiness += percentage;
            }
        }
    }

    public static void connectionUpdated(Level level){
        Component logIn = Component.translatable("multiplayer.player.joined", Component.literal("Herobrine").withStyle(ChatFormatting.OBFUSCATED)).withStyle(ChatFormatting.YELLOW);
        Component logOut = Component.translatable("multiplayer.player.left", Component.literal("Herobrine").withStyle(ChatFormatting.OBFUSCATED)).withStyle(ChatFormatting.YELLOW);

        if (level.getServer() != null && !loggedIn) {
            level.getServer().sendSystemMessage(logIn);
            level.getServer().getPlayerList().broadcastSystemMessage(logIn,false);
        }
        else if (level.getServer() != null && loggedIn){
            level.getServer().getPlayerList().broadcastSystemMessage(logOut,false);
        }
        loggedIn = !loggedIn;
    }

    public static void enqueueSound(Level level, @Nullable Player player, Vec3 pos, SoundEvent sound, SoundSource category, float volume, float pitch, boolean shouldOffset) {
        enqueuedSounds.add(new EnqueuedSound(level, player, pos, sound, category, volume, pitch,shouldOffset));

    }


    public static void chooseBlockSound(Player player) {
        BlockState blockState = null;

        for (BlockPos pos : BlockPos.betweenClosed(player.getBoundingBox().expandTowards(20, 10, 20))) {
            BlockState state = player.level().getBlockState(pos);
            if (!state.isAir() && state.getFluidState().isEmpty()) {
                if (player.getRandom().nextFloat() < 0.05 && player.getRandom().nextBoolean()) {
                    blockState = state;
                    break;
                }
            }

        }
        if (blockState == null) {
            return;
        }
        BlockPos pos = player.getOnPos();
        int xShift = player.getRandom().nextIntBetweenInclusive(-30, 30);
        int yShift = player.getRandom().nextIntBetweenInclusive(-10, 10);
        int zShift = player.getRandom().nextIntBetweenInclusive(-30, 30);
        if (player.level().getBrightness(LightLayer.SKY, player.getOnPos()) > 3) {
            yShift = player.getRandom().nextIntBetweenInclusive(-10, -2);
        }
        if (xShift > -5 && xShift < 5) {
            xShift = player.getRandom().nextIntBetweenInclusive(-30, 30);
        }
        if (zShift > -5 && zShift < 5) {
            zShift = player.getRandom().nextIntBetweenInclusive(-30, 30);
        }

        if (player.getRandom().nextFloat() < 0.2F) {
            player.level().playSound(null, pos.offset(xShift, yShift, zShift), blockState.getSoundType(player.level(), pos, player).getBreakSound(), SoundSource.BLOCKS);
        }else if (player.getRandom().nextFloat() < 0.4F) {
            player.level().playSound(null, pos.offset(xShift, yShift, zShift), blockState.getSoundType(player.level(), pos, player).getPlaceSound(), SoundSource.BLOCKS);
        }else if (player.getRandom().nextFloat() < 0.6F) {
            player.level().playSound(null, pos.offset(xShift, yShift, zShift), blockState.getSoundType(player.level(), pos, player).getStepSound(), SoundSource.BLOCKS);
            if (player.getRandom().nextBoolean()) enqueueSound(player.level(),null, pos.offset(xShift, yShift, zShift).getBottomCenter(), blockState.getSoundType(player.level(), pos, player).getStepSound(), SoundSource.BLOCKS,1.0F,1.0F,true);
        }else if (player.getRandom().nextFloat() < 0.8F) {
            player.level().playSound(null, pos.offset(xShift, yShift, zShift), blockState.getSoundType(player.level(), pos, player).getFallSound(), SoundSource.BLOCKS);
        }else{
            player.level().playSound(null, pos.offset(xShift, yShift, zShift), blockState.getSoundType(player.level(), pos, player).getHitSound(), SoundSource.BLOCKS);
        }
        increasePlayerMoodiness(0.05F);
    }
}
