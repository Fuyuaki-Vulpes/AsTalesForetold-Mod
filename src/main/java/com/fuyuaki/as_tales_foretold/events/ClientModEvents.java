package com.fuyuaki.as_tales_foretold.events;


import com.fuyuaki.as_tales_foretold.api.TickingLogic;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {

    }


    @SubscribeEvent
    private void clientTickingHandler(final ClientTickEvent event){
        TickingLogic.client(event);
    }
}
