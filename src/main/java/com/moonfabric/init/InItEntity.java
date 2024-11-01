package com.moonfabric.init;

import com.moonfabric.Entity.attack_blood;
import com.moonfabric.Entity.flysword;
import com.moonfabric.Entity.line;
import com.moonfabric.Entity.owner_blood;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;

public class InItEntity {
    public static final EntityType<flysword> Fly =
            FabricEntityTypeBuilder.create()
                    .dimensions(EntityDimensions.changing(0.25f,0.25f))
                    .trackRangeBlocks(4)
                    .entityFactory(flysword::new)
                    .build();

    public static final EntityType<line> Line =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(0.1f,0.85f))
                    .trackRangeBlocks(4)
                    .defaultAttributes(ZombieEntity::createZombieAttributes)
                    .entityFactory(line::new)
                    .build();
    public static final EntityType<attack_blood> ATTACK_BLOOD_ENTITY_TYPEttack_blood =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(0.1f,0.85f))
                    .trackRangeBlocks(4)
                    .defaultAttributes(ZombieEntity::createZombieAttributes)
                    .entityFactory(attack_blood::new)
                    .build();
    public static final EntityType<owner_blood> OWNER_BLOOD_ENTITY_TYPE =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(1,1))
                    .trackRangeBlocks(4)
                    .defaultAttributes(IronGolemEntity::createIronGolemAttributes)
                    .entityFactory(owner_blood::new)
                    .build();
}
