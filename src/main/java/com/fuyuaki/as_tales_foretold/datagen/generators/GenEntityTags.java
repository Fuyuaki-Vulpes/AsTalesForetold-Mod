package com.fuyuaki.as_tales_foretold.datagen.generators;

import com.fuyuaki.as_tales_foretold.registries.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;


public class GenEntityTags extends EntityTypeTagsProvider {

    public GenEntityTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Entities.CANNOT_PICKUP).addTag(Tags.EntityTypes.BOSSES)
                ;
    }
}
