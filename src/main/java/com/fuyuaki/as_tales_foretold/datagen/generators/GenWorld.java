package com.fuyuaki.as_tales_foretold.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;


public class GenWorld extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
//            .add(Registries.CONFIGURED_FEATURE, MtaConfigFeatures::bootstrap)
//            .add(Registries.PLACED_FEATURE, MtaPlacedFeatures::bootstrap)
//            .add(Registries.NOISE, MTANoises::bootstrap)
//            .add(Registries.BIOME, MtaBiomes::bootstrap)
//            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MtaBiomeModifiers::bootstrap)
            ;


    public GenWorld(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MODID));
    }
}