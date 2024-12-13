package com.fuyuaki.as_tales_foretold.datagen;

import com.fuyuaki.as_tales_foretold.datagen.generators.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGen {


    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        var datapackRegistries = new DatapackBuiltinEntriesProvider(packOutput, event.getLookupProvider(), GenWorld.BUILDER, Set.of(MODID));


        generator.addProvider(true, GenLoot.create(packOutput,lookupProvider));


        GenBlockTags blockTagGenerator = generator.addProvider(true,
                new GenBlockTags(packOutput, datapackRegistries.getRegistryProvider(), existingFileHelper));

        generator.addProvider(true,
                new GenItemTags(packOutput, datapackRegistries.getRegistryProvider(), blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(true,
                new GenBiomeTags(packOutput, datapackRegistries.getRegistryProvider(), existingFileHelper));

        generator.addProvider(true,
                new GenEntityTags(packOutput, datapackRegistries.getRegistryProvider(), existingFileHelper));


        generator.addProvider(true,
                new GenWorld(packOutput, lookupProvider));

        generator.addProvider(true,
                new EN_US_LangProvider(packOutput));

        generator.addProvider(true,
                new GenSoundDefinition(packOutput,existingFileHelper));

        generator.addProvider(true,
                new GlobalLootModifiers(packOutput,datapackRegistries.getRegistryProvider()));


    }

}
