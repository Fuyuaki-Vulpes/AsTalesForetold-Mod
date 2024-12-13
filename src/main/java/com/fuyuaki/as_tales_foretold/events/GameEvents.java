package com.fuyuaki.as_tales_foretold.events;


import com.fuyuaki.as_tales_foretold.api.logic.TickingLogic;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {


    @SubscribeEvent
    private static void serverTickingHandler(final ServerTickEvent.Pre event){
        TickingLogic.server(event);
    }

    @SubscribeEvent
    private static void entityTickingHandler(final EntityTickEvent.Pre event){
        TickingLogic.entity(event);
    }

    @SubscribeEvent
    private static void playerTickingHandler(final PlayerTickEvent.Pre event){
        TickingLogic.playerTick(event);
    }

    @SubscribeEvent
    private static void entityDeathEvent(final LivingDeathEvent event){
        TickingLogic.entityDeath(event);
    }

}
