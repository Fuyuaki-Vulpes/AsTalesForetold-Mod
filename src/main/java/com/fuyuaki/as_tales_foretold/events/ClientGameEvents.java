package com.fuyuaki.as_tales_foretold.events;


import com.fuyuaki.as_tales_foretold.api.logic.TickingLogic;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {

    @SubscribeEvent
    private static void clientTickingHandler(final ClientTickEvent.Pre event){
        TickingLogic.client(event);
    }
}
