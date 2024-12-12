package com.fuyuaki.as_tales_foretold.datagen.generators;

import com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class GlobalLootModifiers  extends GlobalLootModifierProvider {
    public GlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AsTalesForetoldMod.MODID);
    }

    @Override
    protected void start() {

    }
}
