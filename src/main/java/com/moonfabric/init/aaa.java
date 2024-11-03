package com.moonfabric.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class aaa {
    public static final ItemGroup Moon_Fabric_Tab = FabricItemGroup.builder().displayName(Text.translatable("tab.moonfabric_tab")).icon(()->new ItemStack(init.goldbox)).entries((a, c)->{
        c.add(new ItemStack(init.firecottoncandy));
        c.add(new ItemStack(init.goldcottoncandy));
        c.add(new ItemStack(init.stonecottoncandy));
        c.add(new ItemStack(init.watercottoncandy));
        c.add(new ItemStack(init.woodcottoncandy));

        c.add(new ItemStack(init.abook));
        c.add(new ItemStack(init.Moonstone));
        c.add(new ItemStack(init.goldheart));
        c.add(new ItemStack(init.firehead));
        c.add(new ItemStack(init.grassstone));
        c.add(new ItemStack(init.fallstone));
        c.add(new ItemStack(init.waterstone));
        c.add(new ItemStack(init.rageorb));
        c.add(new ItemStack(init.rageapple));
        c.add(new ItemStack(init.doomfruit));
        c.add(new ItemStack(init.doomstone));
        c.add(new ItemStack(init.doomsoul));
        c.add(new ItemStack(init.cursecandle));
        c.add(new ItemStack(init.curseeye));
        c.add(new ItemStack(init.doomcharm));
        c.add(new ItemStack(init.doomtreasure));
        c.add(new ItemStack(init.curseshield));
        c.add(new ItemStack(init.redstone));
        c.add(new ItemStack(init.bluestone));
        c.add(new ItemStack(init.greenstone));
        c.add(new ItemStack(init.whiteorb));
        c.add(new ItemStack(init.blackorb));
        c.add(new ItemStack(init.blackhead));
        c.add(new ItemStack(init.goldbox));
        c.add(new ItemStack(init.grail));
        c.add(new ItemStack(init.candle));
        c.add(new ItemStack(init.book));
        c.add(new ItemStack(init.twistedorb));
        c.add(new ItemStack(init.twistedsoul));
        c.add(new ItemStack(init.twistedstone));
        c.add(new ItemStack(init.cube));
        c.add(new ItemStack(init.snail));
        c.add(new ItemStack(init.origincube));


        c.add(new ItemStack(init.bloodeye));
        c.add(new ItemStack(init.bloodtime));
        c.add(new ItemStack(init.bloodcharm));
        c.add(new ItemStack(init.furybloodpearl));



        c.add(new ItemStack(init.glodstone));
        c.add(new ItemStack(init.fissionreactor));
        c.add(new ItemStack(init.bloodorb));
        c.add(new ItemStack(init.nanoheart));
        c.add(new ItemStack(init.nanofruit));
        c.add(new ItemStack(init.nanoeye));
        c.add(new ItemStack(init.nanocube));
        c.add(new ItemStack(init.nanocottoncandy));
        c.add(new ItemStack(init.sevensword));

        c.add(new ItemStack(init.pain_box));
        c.add(new ItemStack(init.pain_heart));
        c.add(new ItemStack(init.pain_ring));
        c.add(new ItemStack(init.pain_stone));
        c.add(new ItemStack(init.pain_candle));

        c.add(new ItemStack(init.pain_carrot));
        c.add(new ItemStack(init.pain_book));


        c.add(new ItemStack(init.blood_amout));
        c.add(new ItemStack(init.blood_candle));
        c.add(new ItemStack(init.greedcrystal));
        c.add(new ItemStack(init.mblock));
        c.add(new ItemStack(init.meye));

        c.add(new ItemStack(init.ectoplasmapple));
        c.add(new ItemStack(init.ectoplasmball));
        c.add(new ItemStack(init.ectoplasmbattery));
        c.add(new ItemStack(init.ectoplasmcloub));
        c.add(new ItemStack(init.ectoplasmcube));
        c.add(new ItemStack(init.ectoplasmhorseshoe));
        c.add(new ItemStack(init.ectoplasmprism));
        c.add(new ItemStack(init.ectoplasmshild));
        c.add(new ItemStack(init.ectoplasmstar));

        c.add(new ItemStack(init.nightmareanchor));
        c.add(new ItemStack(init.nightmarecharm));
        c.add(new ItemStack(init.nightmareeye));
        c.add(new ItemStack(init.nightmarerotten));
        c.add(new ItemStack(init.nightmarestone));

    }).build();
    public static final ItemGroup DNA = FabricItemGroup.builder().displayName(Text.translatable("tab.moonfabric_tab.dna")).icon(()->new ItemStack(init.abook)).entries((a, c)->{

        c.add(new ItemStack(init.abook));
        c.add(new ItemStack(init.dna));


        c.add(new ItemStack(DNAItems.atp_height));
        c.add(new ItemStack(DNAItems.cell_acid));
        c.add(new ItemStack(DNAItems.cell_big_boom));
        c.add(new ItemStack(DNAItems.cell_bone_add));
        c.add(new ItemStack(DNAItems.cell_break_down_water));
        c.add(new ItemStack(DNAItems.cell_chromosome));
        c.add(new ItemStack(DNAItems.cell_compress));
        c.add(new ItemStack(DNAItems.cell_constant));
        c.add(new ItemStack(DNAItems.cell_cranial));
        c.add(new ItemStack(DNAItems.cell_darwin));
        c.add(new ItemStack(DNAItems.cell_digestion));
        c.add(new ItemStack(DNAItems.cell_disorder));
        c.add(new ItemStack(DNAItems.cell_dna_suppression));
        c.add(new ItemStack(DNAItems.cell_eyes));
        c.add(new ItemStack(DNAItems.cell_flu));
        c.add(new ItemStack(DNAItems.cell_god));
        c.add(new ItemStack(DNAItems.cell_ground));
        c.add(new ItemStack(DNAItems.cell_in_air));
        c.add(new ItemStack(DNAItems.cell_in_water));
        c.add(new ItemStack(DNAItems.cell_inheritance));
        c.add(new ItemStack(DNAItems.cell_necrosis));
        c.add(new ItemStack(DNAItems.cell_off_on));
        c.add(new ItemStack(DNAItems.cell_oxygen));
        c.add(new ItemStack(DNAItems.cell_preferential));
        c.add(new ItemStack(DNAItems.cell_putrefactive));
        c.add(new ItemStack(DNAItems.cell_sense));
        c.add(new ItemStack(DNAItems.cell_synthesis));
        c.add(new ItemStack(DNAItems.speed_metabolism));

    }).build();
}
