package com.fuyuaki.as_tales_foretold.datagen.generators;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import static com.fuyuaki.as_tales_foretold.api.AsTalesForetoldMod.MODID;

public class GenSoundDefinition extends SoundDefinitionsProvider {
    public GenSoundDefinition(PackOutput output, ExistingFileHelper helper) {
        super(output, MODID, helper);
    }

    @Override
    public void registerSounds() {

    }
}
