package com.moonfabric.init;

import com.moonfabric.Entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WardenBrain;
import net.minecraft.entity.mob.WardenEntity;
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

    public static final EntityType<cell_zombie> cell_zombie =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(1,2))
                    .trackRangeBlocks(10)
                    .defaultAttributes(ZombieEntity::createZombieAttributes)
                    .entityFactory(cell_zombie::new)
                    .build();
    public static final EntityType<nightmare_giant> nightmare_giant =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(1.4F,2))
                    .trackRangeBlocks(16)
                    .defaultAttributes(com.moonfabric.Entity.nightmare_giant::addAttributes)
                    .entityFactory(nightmare_giant::new)
                    .build();

    public static final EntityType<nig_test> nig_test =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(1,1))
                    .trackRangeBlocks(4)
                    .defaultAttributes(WardenEntity::addAttributes)
                    .entityFactory(nig_test::new)
                    .build();

    public static final EntityType<cell_giant> cell_giant =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(1.5F,2))
                    .trackRangeBlocks(16)
                    .defaultAttributes(com.moonfabric.Entity.cell_giant::addAttributes)
                    .entityFactory(cell_giant::new)
                    .build();

    public static final EntityType<head> head =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(0.5f,0.5f))
                    .trackRangeBlocks(48)
                    .defaultAttributes(com.moonfabric.Entity.cell_giant::addAttributes)
                    .entityFactory(head::new)
                    .build();

    public static final EntityType<penalty> penalty =
            FabricEntityTypeBuilder.createLiving()
                    .dimensions(EntityDimensions.changing(0.5f,0.5f))
                    .trackRangeBlocks(48)
                    .defaultAttributes(com.moonfabric.Entity.cell_giant::addAttributes)
                    .entityFactory(penalty::new)
                    .build();
}
