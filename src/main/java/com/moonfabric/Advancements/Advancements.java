package com.moonfabric.Advancements;

import com.moonfabric.init.init;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<AdvancementEntry>>{
    public static String root = "root";
    public static String modid = "moonfabric";
    @Override
    public void accept(Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        init.goldbox,
                Text.literal("Moonfabric!"),
                Text.literal("Start your journey"),
                        Identifier.of("textures/block/cracked_deepslate_tiles.png"), // 使用的背景图片
                AdvancementFrame.TASK,
                true,
                true,
                false
        )
                .criterion(root, InventoryChangedCriterion.Conditions.items(Items.DIRT))
                .build(consumer, modid + "/root");


        AdvancementEntry origincube = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.origincube, Text.literal("origin cube"),
                        Text.literal("The Mysterious Magic Cube of the Creator"), null, AdvancementFrame.TASK,true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("origincube",
                        InventoryChangedCriterion.Conditions.items(init.origincube))
                .build(consumer, modid + "/origincube");

        AdvancementEntry bluestone = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        init.bluestone,
                        Text.literal("blue stone"),
                        Text.literal("I will watch it forever "),
                        null, // 子进度不需要设置背景
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(100))
                .criterion("bluestone", InventoryChangedCriterion.Conditions.items(init.bluestone))
                .build(consumer, modid + "/bluestone");

        AdvancementEntry redstone = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.redstone, Text.literal("red stone"),
                        Text.literal("I will destroy everything"), null, AdvancementFrame.TASK,true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("redstone",
                        InventoryChangedCriterion.Conditions.items(init.redstone))
                .build(consumer, modid + "/redstone");

        AdvancementEntry moonstone = Advancement.Builder.create().parent(redstone).display(
                        init.Moonstone, Text.literal("moon stone"),
                        Text.literal("moonstone"), null, AdvancementFrame.TASK,true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("moonstone",
                        InventoryChangedCriterion.Conditions.items(init.Moonstone))
                .build(consumer, modid + "/moonstone");


        AdvancementEntry goldheart = Advancement.Builder.create().parent(redstone).display(
                        init.goldheart, Text.literal("gold heart"),
                        Text.literal("gold heart"), null, AdvancementFrame.TASK,true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("goldheart",
                        InventoryChangedCriterion.Conditions.items(init.goldheart))
                .build(consumer, modid + "/goldheart");

        AdvancementEntry waterstone = Advancement.Builder.create().parent(redstone).display(
                        init.waterstone, Text.literal("water stone"),
                        Text.literal("the stone of water"), null, AdvancementFrame.TASK,true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("waterstone",
                        InventoryChangedCriterion.Conditions.items(init.waterstone))
                .build(consumer, modid + "/waterstone");




        AdvancementEntry whiteorb = Advancement.Builder.create().parent(bluestone).display(
                init.whiteorb, Text.literal("white orb"),
                        Text.literal("Magic of Light"), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("whiteorb", InventoryChangedCriterion.Conditions.items(init.whiteorb))
                .build(consumer, modid + "/whiteorb");
        AdvancementEntry blackorb = Advancement.Builder.create().parent(whiteorb).display(
                        init.blackorb, Text.literal("black orb"),
                        Text.literal("Dark Magic"), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("blackorb", InventoryChangedCriterion.Conditions.items(init.blackorb))
                .build(consumer, modid + "/blackorb");

        AdvancementEntry blackhead = Advancement.Builder.create().parent(blackorb).display(
                        init.blackhead, Text.literal("black head"),
                        Text.literal("Pure dark magic, but... What is the cost..."), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("blackhead", InventoryChangedCriterion.Conditions.items(init.blackhead))
                .build(consumer, modid + "/blackhead");


        AdvancementEntry cursecandle = Advancement.Builder.create().parent(blackhead).display(
                        init.cursecandle, Text.literal("curse candle"),
                        Text.literal("Stepping on the ashes, I will achieve the glory of the next generation"), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("cursecandle", InventoryChangedCriterion.Conditions.items(init.cursecandle))
                .build(consumer, modid + "/cursecandle");

        AdvancementEntry firehead = Advancement.Builder.create().parent(blackhead).display(
                        init.firehead, Text.literal("fire head"),
                        Text.literal("Is this the price of flames?"), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("firehead", InventoryChangedCriterion.Conditions.items(init.firehead))
                .build(consumer, modid + "/firehead");


        AdvancementEntry greenstone = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.greenstone, Text.literal("green stone"),
                        Text.literal("I will never cease to live "), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("greenstone", InventoryChangedCriterion.Conditions.items(init.greenstone))
                .build(consumer, modid + "/greenstone");
        AdvancementEntry grassstone = Advancement.Builder.create().parent(greenstone).display(
                        init.grassstone, Text.literal("grass stone"),
                        Text.literal("the live "), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("grassstone", InventoryChangedCriterion.Conditions.items(init.grassstone))
                .build(consumer, modid + "/grassstone");
        AdvancementEntry fallstone = Advancement.Builder.create().parent(greenstone).display(
                        init.fallstone, Text.literal("fall stone"),
                        Text.literal("Fall"), null, AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("fallstone", InventoryChangedCriterion.Conditions.items(init.fallstone))
                .build(consumer, modid + "/fallstone");



        AdvancementEntry rageapple = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.rageapple, Text.literal("rage apple"), Text.literal("I'm angry "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("rageapple", InventoryChangedCriterion.Conditions.items(init.rageapple))
                .build(consumer, modid + "/rageapple");

        AdvancementEntry rageorb = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.rageorb, Text.literal("rage orb"), Text.literal("I am very angry "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("rageorb", InventoryChangedCriterion.Conditions.items(init.rageorb))
                .build(consumer, modid + "/rageorb");


        AdvancementEntry doomfruit = Advancement.Builder.create().parent(rageapple).display(
                        init.doomfruit, Text.literal("doom fruit"), Text.literal("Doomsday cuisine "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("doomfruit", InventoryChangedCriterion.Conditions.items(init.doomfruit))
                .build(consumer, modid + "/doomfruit");

        AdvancementEntry doomcharm = Advancement.Builder.create().parent(rageorb).display(
                        init.doomcharm, Text.literal("doom charm"), Text.literal("The Apocalyptic obelisk "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("doomcharm", InventoryChangedCriterion.Conditions.items  (init.doomcharm))
                .build(consumer, modid + "/doomcharm");
        AdvancementEntry curseeye = Advancement.Builder.create().parent(doomcharm).display(
                        init.curseeye, Text.literal("curse eye"), Text.literal("the eye"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("curseeye", InventoryChangedCriterion.Conditions.items  (init.curseeye))
                .build(consumer, modid + "/curseeye");
        AdvancementEntry curseshield = Advancement.Builder.create().parent(doomcharm).display(
                        init.curseshield, Text.literal("curse shield"), Text.literal("curse shield"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("curseshield", InventoryChangedCriterion.Conditions.items  (init.curseshield))
                .build(consumer, modid + "/curseshield");

        AdvancementEntry doomsoul = Advancement.Builder.create().parent(rageorb).display(
                        init.doomsoul, Text.literal("doom soul"), Text.literal("The soul of the apocalypse, it never stops "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("doomsoul", InventoryChangedCriterion.Conditions.items   (init.doomsoul))
                .build(consumer, modid + "/doomsoul");

        AdvancementEntry doomstone = Advancement.Builder.create().parent(rageorb).display(
                        init.doomstone, Text.literal("doom stone"), Text.literal("Doomsday Gambling "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("doomstone", InventoryChangedCriterion.Conditions.items(init.doomstone))
                .build(consumer, modid + "/doomstone");

        AdvancementEntry doomtreasure = Advancement.Builder.create().parent(rageorb).display(
                        init.doomtreasure, Text.literal("doom treasure"), Text.literal("The Greedy Man of the Apocalypse "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("doomtreasure", InventoryChangedCriterion.Conditions.items(init.doomtreasure))
                .build(consumer, modid + "/doomtreasure");












        AdvancementEntry goldbox = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.goldbox, Text.literal("gold box"), Text.literal("Open! "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("goldbox", InventoryChangedCriterion.Conditions.items  (init.goldbox))
                .build(consumer, modid + "/goldbox");

        AdvancementEntry grail = Advancement.Builder.create().parent(goldbox).display(
                        init.grail, Text.literal("grail"), Text.literal("dirk "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("grail", InventoryChangedCriterion.Conditions.items   (init.grail))
                .build(consumer, modid + "/grail");

        AdvancementEntry candle = Advancement.Builder.create().parent(goldbox).display(
                        init.candle, Text.literal("candle"), Text.literal("Blow it out "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("candle", InventoryChangedCriterion.Conditions.items(init.candle))
                .build(consumer, modid + "/candle");

        AdvancementEntry book = Advancement.Builder.create().parent(goldbox).display(
                        init.book, Text.literal("book"), Text.literal("The sea of knowledge "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("book", InventoryChangedCriterion.Conditions.items(init.book))
                .build(consumer, modid + "/book");

        AdvancementEntry bloodcharm = Advancement.Builder.create().parent(redstone).display(
                        init.bloodcharm, Text.literal("bloodcharm"), Text.literal("The Rage "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("bloodcharm", InventoryChangedCriterion.Conditions.items(init.bloodcharm))
                .build(consumer, modid + "/bloodcharm");

        AdvancementEntry furybloodpearl = Advancement.Builder.create().parent(bloodcharm).display(
                        init.furybloodpearl, Text.literal("furybloodpearl"), Text.literal("The Rage Pearl"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("furybloodpearl", InventoryChangedCriterion.Conditions.items(init.furybloodpearl))
                .build(consumer, modid + "/furybloodpearl");









        AdvancementEntry twistedorb = Advancement.Builder.create().parent(candle).display(
                        init.twistedorb, Text.literal("twisted orb"), Text.literal("Look at me "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("twistedorb", InventoryChangedCriterion.Conditions.items  (init.twistedorb))
                .build(consumer, modid + "/twistedorb");

        AdvancementEntry twistedsoul = Advancement.Builder.create().parent(grail).display(
                        init.twistedsoul, Text.literal("twisted soul"), Text.literal("Don't touch me! "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("twistedsoul", InventoryChangedCriterion.Conditions.items   (init.twistedsoul))
                .build(consumer, modid + "/twistedsoul");

        AdvancementEntry twistedstone = Advancement.Builder.create().parent(goldbox).display(
                        init.twistedstone, Text.literal("twisted stone"), Text.literal("What is the price of eternity? "), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("twistedstone", InventoryChangedCriterion.Conditions.items(init.twistedstone))
                .build(consumer, modid + "/twistedstone");

        AdvancementEntry cube = Advancement.Builder.create().parent(book).display(
                        init.cube, Text.literal("cube"), Text.literal("I have many eyes, like another piece of me...... "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("cube", InventoryChangedCriterion.Conditions.items(init.cube))
                .build(consumer, modid + "/cube");

        AdvancementEntry goldcottoncandy = Advancement.Builder.create().parent(cube).display(
                        init.goldcottoncandy, Text.literal("gold cotton candy"), Text.literal("I am greater than wood "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("goldcottoncandy", InventoryChangedCriterion.Conditions.items(init.goldcottoncandy))
                .build(consumer, modid + "/goldcottoncandy");

        AdvancementEntry stonecottoncandy = Advancement.Builder.create().parent(cube).display(
                        init.stonecottoncandy, Text.literal("stone cotton candy"), Text.literal("I am greater than water "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("stonecottoncandy", InventoryChangedCriterion.Conditions.items(init.stonecottoncandy))
                .build(consumer, modid + "/stonecottoncandy");

        AdvancementEntry woodcottoncandy = Advancement.Builder.create().parent(cube).display(
                        init.woodcottoncandy, Text.literal("wood cotton candy"), Text.literal("I am greater than stone "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("woodcottoncandy", InventoryChangedCriterion.Conditions.items(init.woodcottoncandy))
                .build(consumer, modid + "/woodcottoncandy");
        AdvancementEntry firecottoncandy = Advancement.Builder.create().parent(cube).display(
                        init.firecottoncandy, Text.literal("fire cotton candy"), Text.literal("I am greater than gold "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("firecottoncandy", InventoryChangedCriterion.Conditions.items(init.firecottoncandy))
                .build(consumer, modid + "/firecottoncandy");

        AdvancementEntry watercottoncandy = Advancement.Builder.create().parent(cube).display(
                        init.watercottoncandy, Text.literal("water cotton candy"), Text.literal("I am greater than fire "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("watercottoncandy", InventoryChangedCriterion.Conditions.items(init.watercottoncandy))
                .build(consumer, modid + "/watercottoncandy");

        AdvancementEntry glodstone = Advancement.Builder.create().parent(origincube).display(
                        init.glodstone, Text.literal("§6Primitive coat of arms"), Text.literal("§6Primitive..... "), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("glodstone", InventoryChangedCriterion.Conditions.items(init.glodstone))
                .build(consumer, modid + "/glodstone");
        AdvancementEntry bloodorb = Advancement.Builder.create().parent(furybloodpearl).display(
                        init.bloodorb, Text.literal("§6Your Heart Eye"), Text.literal("§6Sinful and disgusting"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("bloodorb", InventoryChangedCriterion.Conditions.items(init.bloodorb))
                .build(consumer, modid + "/bloodorb");



        AdvancementEntry nanoheart = Advancement.Builder.create().parent(blackhead).display(
                        init.nanoheart, Text.literal("Nanos"), Text.literal("nano heart"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("nanoheart", InventoryChangedCriterion.Conditions.items(init.nanoheart))
                .build(consumer, modid + "/nanoheart");


        AdvancementEntry nanofruit = Advancement.Builder.create().parent(nanoheart).display(
                        init.nanofruit, Text.literal("the fruit"), Text.literal("nano fruit"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("nanofruit", InventoryChangedCriterion.Conditions.items(init.nanofruit))
                .build(consumer, modid + "/nanofruit");
        AdvancementEntry nanoeye = Advancement.Builder.create().parent(nanoheart).display(
                        init.nanoeye, Text.literal("the eye"), Text.literal("nano eye"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("nanoeye", InventoryChangedCriterion.Conditions.items(init.nanoeye))
                .build(consumer, modid + "/nanoeye");

        AdvancementEntry nanocube = Advancement.Builder.create().parent(nanoeye).display(
                        init.nanocube, Text.literal("the cube 2"), Text.literal("nano cube"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("nanocube", InventoryChangedCriterion.Conditions.items(init.nanocube))
                .build(consumer, modid + "/nanocube");

        AdvancementEntry nanocottoncandy = Advancement.Builder.create().parent(nanoeye).display(
                        init.nanocottoncandy, Text.literal("The Nano cotton candy"), Text.literal("Luck Cotton candy"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("nanocottoncandy", InventoryChangedCriterion.Conditions.items(init.nanocottoncandy))
                .build(consumer, modid + "/nanocottoncandy");

        AdvancementEntry bloodeye = Advancement.Builder.create().parent(bloodorb).display(
                        init.bloodeye, Text.literal("The orb ring?"), Text.literal("luck age"), null,AdvancementFrame.GOAL, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("bloodeye", InventoryChangedCriterion.Conditions.items(init.bloodeye))
                .build(consumer, modid + "/bloodeye");

        AdvancementEntry bloodtime = Advancement.Builder.create().parent(bloodorb).display(
                        init.bloodtime, Text.literal("Time"), Text.literal("die time"), null,AdvancementFrame.GOAL, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("bloodtime", InventoryChangedCriterion.Conditions.items(init.bloodtime))
                .build(consumer, modid + "/bloodtime");


        AdvancementEntry sevensword = Advancement.Builder.create().parent(nanoheart).display(
                        init.sevensword, Text.literal("We! Nanos!"), Text.literal("seven sword"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("sevensword", InventoryChangedCriterion.Conditions.items(init.sevensword))
                .build(consumer, modid + "/sevensword");

        AdvancementEntry pain_box = Advancement.Builder.create().parent(rootAdvancement).display(
                        init.pain_box, Text.literal("He looks very painful"), Text.literal("pain box"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("pain_box", InventoryChangedCriterion.Conditions.items(init.pain_box))
                .build(consumer, modid + "/pain_box");
        AdvancementEntry pain_candle = Advancement.Builder.create().parent(pain_box).display(
                        init.pain_candle, Text.literal("Cave candles"), Text.literal("pain candle"), null,AdvancementFrame.CHALLENGE, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("pain_candle", InventoryChangedCriterion.Conditions.items(init.pain_candle))
                .build(consumer, modid + "/pain_candle");


        AdvancementEntry pain_heart = Advancement.Builder.create().parent(pain_candle).display(
                        init.pain_heart, Text.literal("Never stop"), Text.literal("pain heart"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("pain_heart", InventoryChangedCriterion.Conditions.items(init.pain_heart))
                .build(consumer, modid + "/pain_heart");

        AdvancementEntry pain_ring = Advancement.Builder.create().parent(pain_heart).display(
                        init.pain_ring, Text.literal("Never die"), Text.literal("pain ring"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("pain_ring", InventoryChangedCriterion.Conditions.items(init.pain_ring))
                .build(consumer, modid + "/pain_ring");
        AdvancementEntry pain_stone = Advancement.Builder.create().parent(pain_candle).display(
                        init.pain_stone, Text.literal("When the wind blows through its hole, it makes a sharp sound"), Text.literal("pain stone"), null,AdvancementFrame.TASK, true,true, false).rewards(AdvancementRewards.Builder.experience(100))
                .criterion("pain_stone", InventoryChangedCriterion.Conditions.items(init.pain_stone))
                .build(consumer, modid + "/pain_stone");

    }
}
