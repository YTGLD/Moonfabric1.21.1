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


    public AttReg(){
        Registry.register(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,"heal"),heal.value());
    }

    private static RegistryEntry<EntityAttribute> reg(EntityAttribute entityAttribute, String name) {
        return Registry.registerReference(Registries.ATTRIBUTE,Identifier.of(MoonFabricMod.MODID,name),entityAttribute);
    }

}
