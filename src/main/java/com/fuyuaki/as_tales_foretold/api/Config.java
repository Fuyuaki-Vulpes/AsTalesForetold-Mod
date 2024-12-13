package com.fuyuaki.as_tales_foretold.api;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;


@EventBusSubscriber(modid = AsTalesForetoldMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue PICK_UP_MOB_DROPS = BUILDER
            .comment("How often should Herobrine pick up mob drops? 0 = Disable (One in X)")
            .defineInRange("pickUpMobDrops", 800, 0, Integer.MAX_VALUE);


    private static final ModConfigSpec.IntValue BLOCK_INTERACTION_RATE = BUILDER
            .comment("How often should Herobrine make block noises around you? 0 = Disable (One in X)")
            .defineInRange("blockInteractionRate", 3000, 0, Integer.MAX_VALUE);

    private static final ModConfigSpec.DoubleValue MIMICRY_RATE = BUILDER
            .comment("Percentage Chance of making a mimicry. 0 = Disable")
            .defineInRange("mimicryRate", 0.01, 0, 1.0);
    private static final ModConfigSpec.IntValue MIMICRY_TIMER = BUILDER
            .comment("Ticks to wait for another mimicry attempt, 20 ticks = 1 sec")
            .defineInRange("mimicryTimer", 1226, 0, Integer.MAX_VALUE);


    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int pickUpMobDrops;
    public static int blockInteractionRate;
    public static double mimicryRate;
    public static int mimicryTimer;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        pickUpMobDrops = PICK_UP_MOB_DROPS.get();
        blockInteractionRate = BLOCK_INTERACTION_RATE.get();
        mimicryRate = MIMICRY_RATE.get();
        mimicryTimer = MIMICRY_TIMER.get();


    }
}
