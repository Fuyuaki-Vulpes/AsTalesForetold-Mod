package com.fuyuaki.as_tales_foretold.events;


import com.fuyuaki.as_tales_foretold.api.TickingLogic;
import com.fuyuaki.as_tales_foretold.network.NetworkHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }

    @SubscribeEvent
    private void serverTickingHandler(final ServerTickEvent event){
        TickingLogic.server(event);
    }

    @SubscribeEvent
    private void entityTickingHandler(final EntityTickEvent event){
        TickingLogic.entity(event);
    }

    @SubscribeEvent
    private void playerTickingHandler(final PlayerTickEvent event){
        TickingLogic.playerTick(event);
    }

    @SubscribeEvent
    private void entityDeathEvent(final LivingDeathEvent event){
        TickingLogic.entityDeath(event);
    }



}
