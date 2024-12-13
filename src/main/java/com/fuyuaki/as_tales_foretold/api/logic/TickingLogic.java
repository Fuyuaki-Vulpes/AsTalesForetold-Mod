package com.fuyuaki.as_tales_foretold.api.logic;

import com.fuyuaki.as_tales_foretold.api.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.Random;

public class TickingLogic {

    public static final Random RANDOM = new Random();
    public static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void server(ServerTickEvent.Pre event) {
        HerobrineSystem.tickServer(event.getServer().overworld());

    }

    public static void client(ClientTickEvent.Pre event) {
        HerobrineSystem.tickClient();
    }


    public static void entity(EntityTickEvent.Pre event) {
    }

    public static void entityDeath(LivingDeathEvent event) {
    }
    public static void playerTick(PlayerTickEvent.Pre event) {
        Mimic.tick(event.getEntity());
        if (RANDOM.nextDouble() < 1.0F / (double)Config.blockInteractionRate){
            HerobrineSystem.chooseBlockSound(event.getEntity());
        }
    }
}
