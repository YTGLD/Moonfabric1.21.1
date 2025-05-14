package com.moonfabric.init;

import com.moonfabric.ABook;
import com.moonfabric.item.Ms.CottonCandy.*;
import com.moonfabric.item.Ms.block.*;
import com.moonfabric.item.Ms.extend.ItemTir;
import com.moonfabric.item.Ms.origincube;
import com.moonfabric.item.TheNecora.*;
import com.moonfabric.item.common.*;
import com.moonfabric.item.common.Blood.*;
import com.moonfabric.item.common.CurseOrDoom.*;
import com.moonfabric.item.common.Mise.*;
import com.moonfabric.item.common.NaNo.exc.thedoomeye_ui;
import com.moonfabric.item.common.NaNo.exc.thedoomeye_ui1;
import com.moonfabric.item.common.NaNo.*;
import com.moonfabric.item.common.max.glodstone;
import com.moonfabric.item.common.pain.*;
import com.moonfabric.item.dna.dna;
import com.moonfabric.item.dna.medicinebox;
import com.moonfabric.item.dna.med.*;
import com.moonfabric.item.ectoplasm.*;
import com.moonfabric.item.necora;
import com.moonfabric.item.nightmare.*;
import com.moonfabric.item.sevensword;
import com.moonfabric.item.nightmare.super_nightmare.*;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class init  {
    public static final Item fire_book = new ItemTir(){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(Text.translatable("item.fire_book.tool.string").formatted(Formatting.GOLD));

        }
    };
    public static final Item the_blood_book = new  the_blood_book();

    public static final Item nightmare_base_black_eye = new  nightmare_base_black_eye();
    public static final Item nightmare_base = new  nightmare_base();

    public static final Item nightmare_base_black_eye_eye = new  nightmare_base_black_eye_eye();
    public static final Item nightmare_base_black_eye_heart = new  nightmare_base_black_eye_heart();

    public static final Item nightmare_base_black_eye_red = new  nightmare_base_black_eye_red();
    public static final Item nightmare_base_stone = new  nightmare_base_stone();
    public static final Item nightmare_base_stone_meet = new  nightmare_base_stone_meet();

    public static final Item nightmare_base_stone_virus = new  nightmare_base_stone_virus();
    public static final Item nightmare_base_stone_brain = new  nightmare_base_stone_brain();

    public static final Item nightmare_virus = new  nightmare_virus();
    public static final Item nightmare_base_reversal =new  nightmare_base_reversal();

    public static final Item nightmare_base_reversal_orb =new  nightmare_base_reversal_orb();
    public static final Item nightmare_base_reversal_card =new  nightmare_base_reversal_card();
    public static final Item nightmare_base_reversal_mysterious =new  nightmare_base_reversal_mysterious();

    public static final Item nightmare_base_redemption = new  nightmare_base_redemption();
    public static final Item nightmare_base_redemption_deception = new  nightmare_base_redemption_deception();
    public static final Item nightmare_base_redemption_degenerate = new  nightmare_base_redemption_degenerate();
    public static final Item nightmare_base_redemption_down_and_out = new  nightmare_base_redemption_down_and_out();
    public static final Item nightmare_base_fool = new  nightmare_base_fool();
    public static final Item nightmare_base_fool_soul = new  nightmare_base_fool_soul();
    public static final Item nightmare_base_fool_bone = new  nightmare_base_fool_bone();
    public static final Item nightmare_base_fool_betray = new  nightmare_base_fool_betray();
    public static final Item nightmare_base_insight = new  nightmare_base_insight();
    public static final Item nightmare_base_insight_drug = new  nightmare_base_insight_drug();
    public static final Item nightmare_base_insight_insane = new  nightmare_base_insight_insane();
    public static final Item nightmare_base_insight_collapse = new  nightmare_base_insight_collapse();
    public static final Item nightmare_base_start = new  nightmare_base_start();
    public static final Item nightmare_base_start_pod = new  nightmare_base_start_pod();
    public static final Item nightmare_base_start_egg = new  nightmare_base_start_egg();

    public static final Item nightmare_base_start_power = new  nightmare_base_start_power();
    public static final Item medicinebox =new  medicinebox();
    public static final Item calcification = new  calcification();
    public static final Item masticatory = new  masticatory();
    public static final Item polyphagia = new  polyphagia();
    public static final Item quadriceps = new  quadriceps();
    public static final Item reanimation = new  reanimation();

    public static final Item Moonstone = new moonstone();
    public static final Item goldheart = new goldheart();
    public static final Item firehead = new firehead();
    public static final Item grassstone = new grassstone();
    public static final Item fallstone = new fallstone();
    public static final Item waterstone = new waterstone();
    public static final Item rageorb = new rageorb();
    public static final Item rageapple = new rageapple();
    public static final Item doomfruit = new doomfruit();
    public static final Item doomstone = new doomstone();
    public static final Item doomsoul = new doomsoul();
    public static final Item cursecandle = new cursecandle();
    public static final Item curseeye = new curseeye();
    public static final Item doomcharm = new doomcharm();
    public static final Item doomtreasure = new doomtreasure();

    public static final Item curseshield = new curseshield();
    public static final Item redstone = new redstone();
    public static final Item bluestone = new bluestone();

    public static final Item greenstone = new greenstone();
    public static final Item blackorb = new blackorb();
    public static final Item whiteorb = new whiteorb();
    public static final Item blackhead = new blackhead();

    public static final Item goldbox = new goldbox();

    public static final Item grail = new grail();
    public static final Item candle = new candle();
    public static final Item book = new book();
    public static final Item twistedorb = new twistedorb();
    public static final Item twistedsoul = new twistedsoul();
    public static final Item twistedstone = new twistedstone();
    public static final Item cube = new twistedcube();
    public static final Item goldcottoncandy = new goldcottoncandy();
    public static final Item woodcottoncandy = new woodcottoncandy();
    public static final Item stonecottoncandy = new stonecottoncandy();
    public static final Item watercottoncandy = new watercottoncandy();
    public static final Item firecottoncandy = new firecottoncandy();
    public static final Item snail = new snail();
    public static final Item origincube = new origincube();
    public static final Item abook = new ABook();

    public static final Item gazer = new gazer();

    public static final Item bloodcharm = new bloodcharm();
    public static final Item furybloodpearl = new furybloodpearl();
    public static final Item glodstone = new glodstone();
    public static final Item fissionreactor = new fissionreactor();
    public static final Item bloodorb = new bloodorb();
    public static final Item nanoheart = new nanoheart();
    public static final Item nanofruit = new nanofruit();
    public static final Item nanoeye = new nanoeye();
    public static final Item nanocube = new nanocube();

    public static final Item thedoomeye_ui = new thedoomeye_ui();
    public static final Item thedoomeye_ui1 = new thedoomeye_ui1();
    public static  final Item sevensword = new sevensword();
    public static final Item nanocottoncandy = new nanocottoncandy();
    public static final Item bloodeye = new bloodeye();
    public static final Item bloodtime = new bloodtime();
    public static final Item pain_box = new pain_box();
    public static final Item pain_heart = new pain_heart();
    public static final Item pain_ring = new pain_ring();
    public static final Item pain_stone = new pain_stone();
    public static final Item pain_candle = new pain_candle();
    public static final Item pain_book = new pain_book();
    public static final Item pain_carrot = new pain_carrot();
    public static final Item blood_amout = new blood_amout();

    public static final Item blood_candle = new blood_candle();
    public static final Item greedcrystal = new greedcrystal();
    public static final Item mblock = new mblock();
    public static final Item meye = new meye();

    public static final Item ectoplasmapple = new ectoplasmapple();
    public static final Item ectoplasmball = new ectoplasmball();
    public static final Item ectoplasmbattery = new ectoplasmbattery();
    public static final Item ectoplasmcloub = new ectoplasmcloub();
    public static final Item ectoplasmcube = new ectoplasmcube();
    public static final Item ectoplasmhorseshoe = new ectoplasmhorseshoe();
    public static final Item ectoplasmprism = new ectoplasmprism();
    public static final Item ectoplasmshild = new ectoplasmshild();

    public static final Item ectoplasmstar = new ectoplasmstar();

    public static final Item nightmareanchor = new nightmareanchor();
    public static final Item nightmarecharm = new nightmarecharm();
    public static final Item nightmareeye = new nightmareeye();
    public static final Item nightmarerotten = new nightmarerotten();
    public static final Item nightmarestone = new nightmarestone();
    public static final Item dna = new dna();

    public static final Item ambush = new ambush();
    public static final Item atpoverdose = new atpoverdose();
    public static final Item autolytic = new autolytic();
    public static final Item fermentation = new fermentation();
    public static final Item putrefactive = new putrefactive();
    public static final Item regenerative = new regenerative();
    public static final Item necora = new necora();
    public static final Item adrenaline = new adrenaline();
    public static final Item anaerobic_cell = new anaerobic_cell();
    public static final Item bone_cell = new bone_cell();
    public static final Item cell = new cell();
    public static final Item cell_blood = new cell_blood();
    public static final Item cell_boom = new cell_boom();
    public static final Item cell_calcification = new cell_calcification();
    public static final Item cell_mummy = new cell_mummy();
    public static final Item disgusting_cells = new disgusting_cells();
    public static final Item giant = new giant();
    public static final Item giant_boom_cell = new giant_boom_cell();
    public static final Item giant_nightmare = new giant_nightmare();
    public static final Item mother_cell = new mother_cell();
    public static final Item parasitic_cell = new parasitic_cell();
    public static final Item subspace_cell = new subspace_cell();

    public static final Item double_head = new double_head();
    public static final Item death_penalty = new death_penalty();
    public static final Item undead_head = new undead_head();
    public static final Item blood_stones = new blood_stones();

    public static final Item owner_blood_eye = new BooksItem(
            "item.moonfabric.owner_blood_eye.tool"
    );
    public static final Item owner_blood_attack_eye = new BooksItem(
            "item.moonfabric.owner_blood_attack_eye.tool"
            , "item.moonfabric.owner_blood_attack_eye.tool.1");
    public static final Item owner_blood_speed_eye = new BooksItem(

            "item.moonfabric.owner_blood_speed_eye.tool",
            "item.moonfabric.owner_blood_speed_eye.tool.1",
            "item.moonfabric.owner_blood_speed_eye.tool.2"

    );
    public static final Item owner_blood_effect_eye = new BooksItem(

            "item.moonfabric.owner_blood_effect_eye.tool",
            "item.moonfabric.owner_blood_effect_eye.tool.1"
    );
    public static final Item owner_blood_boom_eye = new BooksItem(
            "item.moonfabric.owner_blood_boom_eye.tool",
            "item.moonfabric.owner_blood_boom_eye.tool.1",
            "item.moonfabric.owner_blood_boom_eye.tool.2",
            "item.moonfabric.owner_blood_boom_eye.tool.3",
            "item.moonfabric.owner_blood_boom_eye.tool.4"
    );
    public static final Item owner_blood_vex = new BooksItem(
            "item.moonfabric.owner_blood_vex.tool",
            "item.moonfabric.owner_blood_vex.tool.1",
            "item.moonfabric.owner_blood_vex.tool.2"
    );
    public static final Item owner_blood_earth = new BooksItem(
            "item.moonfabric.owner_blood_earth.tool",
            "item.moonfabric.owner_blood_earth.tool.1",
            "item.moonfabric.owner_blood_earth.tool.2",
            "item.moonfabric.owner_blood_earth.tool.3"

    );

    public static final Item pain_box_ui = new Item(new Item.Settings().maxCount(1));



    public static final Block block_amout = new BlockAmout();
    public static final Block block_hand_black = new BlockHandBlack();
    public static final Block block_cube = new BlockCube();
    public static final Block cursecandleblock = new cursecandleblock();
    public static final Block blockbloodorb = new Blockbloodorb();


}
