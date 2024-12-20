package com.moonfabric.init;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

import static net.minecraft.loot.LootTables.TRIAL_CHAMBERS_CORRIDOR_POT;

public class moonfabricLoot {
    public static final List<RegistryKey<LootTable>> TRIAL_CHAMBERS =List.of(
            TRIAL_CHAMBERS_CORRIDOR_POT);


    public static final List<RegistryKey<LootTable>> LOOTTABLES =List.of(LootTables.ANCIENT_CITY_CHEST);
    public static final List<RegistryKey<LootTable>> FIRE =List.of(LootTables.BASTION_TREASURE_CHEST);
    public static final List<RegistryKey<LootTable>> FIRE1 =List.of(LootTables.BASTION_BRIDGE_CHEST);
    public static final List<RegistryKey<LootTable>> FIRE2 =List.of(LootTables.BASTION_HOGLIN_STABLE_CHEST);
    public static final List<RegistryKey<LootTable>> FIRE3 =List.of(LootTables.BASTION_OTHER_CHEST);
    public static final List<RegistryKey<LootTable>> GRA =List.of(LootTables.JUNGLE_TEMPLE_CHEST);
    public static final List<RegistryKey<LootTable>> GRASS =List.of(LootTables.SIMPLE_DUNGEON_CHEST,LootTables.ABANDONED_MINESHAFT_CHEST);
    public static final List<RegistryKey<LootTable>> FALL =List.of(LootTables.DESERT_PYRAMID_CHEST);
    public static final List<RegistryKey<LootTable>> WATER1 =List.of(LootTables.UNDERWATER_RUIN_BIG_CHEST);
    public static final List<RegistryKey<LootTable>> WATER2 =List.of(LootTables.UNDERWATER_RUIN_SMALL_CHEST);
    public static final List<RegistryKey<LootTable>> ABANDONED_MINESHAFT_CHEST =List.of(LootTables.ABANDONED_MINESHAFT_CHEST);

    public static final List<RegistryKey<LootTable>> END =List.of(LootTables.END_CITY_TREASURE_CHEST);

    public static final List<RegistryKey<LootTable>> WOODLAND_MANSION_CHEST =List.of(LootTables.WOODLAND_MANSION_CHEST);

    public static void onLootTableLoad(RegistryKey<LootTable> id, LootTable.Builder supplier) {

        if (LOOTTABLES.contains(id)
                || GRASS.contains(id)
                ||ABANDONED_MINESHAFT_CHEST.contains(id)){
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmball).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.2f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmcloub).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.1f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmcube).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmhorseshoe).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmapple).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmshild).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmbattery).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.ectoplasmstar).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));


            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.greedcrystal).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
        }


        if (TRIAL_CHAMBERS.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_box)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_heart)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_ring)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_stone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_candle)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_carrot)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.pain_book)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))));

        }



        if (WOODLAND_MANSION_CHEST.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.curseshield)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
                supplier.pool(LootPool.builder()
                        .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(init.woodcottoncandy)
                                .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }


        if (END.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.rageorb)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.rageapple)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomfruit)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomsoul)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomcharm)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomtreasure)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }

        if (ABANDONED_MINESHAFT_CHEST.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomtreasure)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }



        if (LOOTTABLES.contains(id)) {
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.Moonstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.goldheart)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.fissionreactor)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.04f)))));

            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.nanofruit)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.02f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.nanoheart)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.02f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.nanoeye)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.02f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.nanocottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.bloodeye)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.bloodtime)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))

                    .with(ItemEntry.builder(init.necora)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));


            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_eye).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_attack_eye).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_earth).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_boom_eye).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_vex).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_effect_eye).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));
            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.owner_blood_speed_eye).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))));


        }
        if (FIRE.contains(id)||FIRE1.contains(id)||FIRE2.contains(id)||FIRE3.contains(id)||GRASS.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.double_head)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.death_penalty)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
        }
        if (FIRE.contains(id)||FIRE1.contains(id)||FIRE2.contains(id)||FIRE3.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.firehead)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.cursecandle)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.goldcottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.firecottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }
        if (GRA.contains(id) ||GRASS.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.grassstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.woodcottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.undead_head)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }

        if (GRASS.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.doomtreasure)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.sevensword)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.redstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.bluestone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.greenstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.whiteorb)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.blackorb)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.blackhead)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.stonecottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.gazer)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.bloodcharm)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.furybloodpearl)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

            supplier.pool(LootPool.builder().bonusRolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(init.blood_stones).apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
        }
        if (FALL.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.grassstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.curseeye)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));


            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.goldbox)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.grail)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.candle)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.book)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.twistedorb)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.twistedsoul)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.twistedstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.cube)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.fallstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.snail)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.blood_amout)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.blood_candle)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));


        }
        if (
                WATER1.contains(id)||
                WATER2.contains(id)){
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.waterstone)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));
            supplier.pool(LootPool.builder()
                    .bonusRolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(init.watercottoncandy)
                            .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.03f)))));

        }
    }

}
