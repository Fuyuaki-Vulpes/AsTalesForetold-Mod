package com.fuyuaki.as_tales_foretold.events;


import com.fuyuaki.as_tales_foretold.network.NetworkHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    private static void commonSetup(final FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }





}
