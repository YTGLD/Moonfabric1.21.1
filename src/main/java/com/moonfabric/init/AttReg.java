package com.moonfabric.init;

import com.moonfabric.MoonFabricMod;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class AttReg {
    public static final RegistryEntry<EntityAttribute> heal =
            reg(new ClampedEntityAttribute(
                    "moonfabric.heal.attribute",1,-1024,1024)
                    .setTracked(true),"heal");
    public static final RegistryEntry<EntityAttribute> cit =
            reg(new ClampedEntityAttribute(
                    "moonfabric.cit.attribute",1,-1024,1024)
                    .setTracked(true),"cit");
    public static final RegistryEntry<EntityAttribute> swiming =
            reg(new ClampedEntityAttribute(
                    "moonfabric.swiming.attribute",1,-1024,1024)
                    .setTracked(true),"swiming");


    public AttReg(){
        Registry.register(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,"heal"),heal.value());
        Registry.register(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,"swiming"),swiming.value());
        Registry.register(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,"cit"),cit.value());
    }

    private static RegistryEntry<EntityAttribute> reg(EntityAttribute entityAttribute, String name) {
        return Registry.registerReference(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,name),entityAttribute);
    }

}
