package com.moonfabric.init;

import com.moonfabric.data.BundleContentsComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class Data {
    public static final Registry<ComponentType<?>> REGISTRY = Registries.DATA_COMPONENT_TYPE;

    public static final ComponentType<NbtCompound> CUSTOM_DATA = reg((builder ->
            builder.codec(NbtCompound.CODEC)));

    public static final ComponentType<BundleContentsComponent> BUNDLE_CONTENTS = reg((builder ->
            builder.codec(BundleContentsComponent.CODEC)));





    public Data(){
        Registry.register(REGISTRY, Identifier.of("moonfabric","string"),CUSTOM_DATA);
        Registry.register(REGISTRY, Identifier.of("moonfabric","bundle"),BUNDLE_CONTENTS);
    }
    private static <T> ComponentType<T> reg(UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return (builderOperator.apply(ComponentType.builder())).build();
    }

}
