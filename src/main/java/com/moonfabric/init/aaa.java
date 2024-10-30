package com.moonfabric.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class aaa {
    public static final ItemGroup Moon_Fabric_Tab = FabricItemGroup.builder().displayName(Text.translatable("tab.moonfabric_tab")).icon(()->new ItemStack(init.goldbox)).entries((a, c)->{
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
    }).build();

}
