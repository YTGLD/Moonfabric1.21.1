package com.moonfabric;

import com.moonfabric.Advancements.AdvancementsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoonFabricModGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {

        generator.createPack().addProvider(AdvancementsProvider::new);

    }
}
