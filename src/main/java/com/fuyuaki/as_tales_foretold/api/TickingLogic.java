package com.fuyuaki.as_tales_foretold.api;

import com.fuyuaki.as_tales_foretold.registries.ModTags;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class TickingLogic {


    public static void server(ServerTickEvent event) {

    }

    public static void client(ClientTickEvent event) {

    }


    public static void entity(EntityTickEvent event) {

    }

    public static void entityDeath(LivingDeathEvent event) {
        if (!event.getEntity().getType().is(ModTags.Entities.CANNOT_PICKUP)){

        }
    }
    public static void playerTick(PlayerTickEvent event) {

    }
}
