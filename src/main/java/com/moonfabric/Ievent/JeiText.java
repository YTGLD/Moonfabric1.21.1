package com.moonfabric.Ievent;


import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.init;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;


@JeiPlugin
public class JeiText implements IModPlugin {
    private static final Identifier UID = Identifier.of(MoonFabricMod.MODID, "jei_plugin");

    @Override
    public @NotNull Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        {

            registration.addIngredientInfo(new ItemStack(init.twistedstone), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.twistedstone"));


            registration.addIngredientInfo(new ItemStack(init.ambush), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ambush").append(Text.translatable("moonfabric.jei.necora.all")));
            registration.addIngredientInfo(new ItemStack(init.atpoverdose), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.atpoverdose").append(Text.translatable("moonfabric.jei.necora.all")));
            registration.addIngredientInfo(new ItemStack(init.autolytic), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.autolytic").append(Text.translatable("moonfabric.jei.necora.all")));
            registration.addIngredientInfo(new ItemStack(init.fermentation), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.fermentation").append(Text.translatable("moonfabric.jei.necora.all")));
            registration.addIngredientInfo(new ItemStack(init.putrefactive), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.putrefactive").append(Text.translatable("moonfabric.jei.necora.all")));
            registration.addIngredientInfo(new ItemStack(init.regenerative), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.regenerative").append(Text.translatable("moonfabric.jei.necora.all")));



            registration.addIngredientInfo(new ItemStack(init.anaerobic_cell), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.anaerobic_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all")));
            registration.addIngredientInfo(new ItemStack(init.giant_boom_cell), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.giant_boom_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all")));
            registration.addIngredientInfo(new ItemStack(init.subspace_cell), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.subspace_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all")));

            registration.addIngredientInfo(new ItemStack(init.cell_mummy), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.adrenaline").append(Text.translatable("moonfabric.jei.cell.all")));
            registration.addIngredientInfo(new ItemStack(init.cell_boom), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.cell_mummy").append(Text.translatable("moonfabric.jei.cell.all")));
            registration.addIngredientInfo(new ItemStack(init.cell_calcification), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.cell_boom").append(Text.translatable("moonfabric.jei.cell.all")));
            registration.addIngredientInfo(new ItemStack(init.cell_blood), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.cell_blood").append(Text.translatable("moonfabric.jei.cell.all")));
            registration.addIngredientInfo(new ItemStack(init.adrenaline), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.cell_calcification").append(Text.translatable("moonfabric.jei.cell.all")));

            registration.addIngredientInfo(new ItemStack(init.nightmareanchor), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nightmareanchor").append(Text.translatable("moonfabric.jei.nightmare")));
            registration.addIngredientInfo(new ItemStack(init.nightmarecharm), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nightmarecharm").append(Text.translatable("moonfabric.jei.nightmare")));
            registration.addIngredientInfo(new ItemStack(init.nightmareeye), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nightmareeye").append(Text.translatable("moonfabric.jei.nightmare")));

            registration.addIngredientInfo(new ItemStack(init.nightmarerotten), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nightmarerotten").append(Text.translatable("moonfabric.jei.nightmare")));
            registration.addIngredientInfo(new ItemStack(init.nightmarestone), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nightmarestone").append(Text.translatable("moonfabric.jei.nightmare")));



           registration.addIngredientInfo(new ItemStack(init.mblock), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.mblock").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.maulice.all")));

          registration.addIngredientInfo(new ItemStack(init.meye), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.meye").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.maulice.all")));



            registration.addIngredientInfo(new ItemStack(init.ectoplasmball), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmball").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.ectoplasmball")));
            registration.addIngredientInfo(new ItemStack(init.ectoplasmcloub), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmcloub").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.ectoplasmcloub")));

            registration.addIngredientInfo(new ItemStack(init.ectoplasmshild), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmshild").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all")));
            registration.addIngredientInfo(new ItemStack(init.ectoplasmapple), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmapple").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all")));
            registration.addIngredientInfo(new ItemStack(init.ectoplasmprism), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmprism").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all")));
            registration.addIngredientInfo(new ItemStack(init.ectoplasmcube), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmcube").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all")));
            registration.addIngredientInfo(new ItemStack(init.ectoplasmstar), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmstar").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all")));






            registration.addIngredientInfo(new ItemStack(init.ectoplasmhorseshoe), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.ectoplasmhorseshoe").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all")));


            registration.addIngredientInfo(new ItemStack(init.greedcrystal), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.greedcrystal").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all")));
            registration.addIngredientInfo(new ItemStack(init.whiteorb), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.whiteorb").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all")));


            registration.addIngredientInfo(new ItemStack(init.nanocube), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.nanocube").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.nanodoom.all")));

            registration.addIngredientInfo(new ItemStack(init.calcification), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.calcification").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.calcification")));
            registration.addIngredientInfo(new ItemStack(init.masticatory), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.masticatory").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.masticatory")));
            registration.addIngredientInfo(new ItemStack(init.polyphagia), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.polyphagia").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.polyphagia")));
            registration.addIngredientInfo(new ItemStack(init.quadriceps), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.quadriceps").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.quadriceps")));
            registration.addIngredientInfo(new ItemStack(init.reanimation), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.reanimation").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.reanimation")));

            registration.addIngredientInfo(new ItemStack(init.blood_amout), VanillaTypes.ITEM_STACK, Text.translatable("item.moonfabric.blood_amout").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.blood.all")));
            registration.addIngredientInfo(init.owner_blood_attack_eye.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_speed_eye.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_eye.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_effect_eye.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_boom_eye.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_vex.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));
            registration.addIngredientInfo(init.owner_blood_earth.getDefaultStack(), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.blood.all"));



            registration.addIngredientInfo(new ItemStack(init.nightmare_base_black_eye_heart), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_heart").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_black_eye_eye), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_eye").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_black_eye_red), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_red").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye"))));

            registration.addIngredientInfo(new ItemStack(init.nightmare_base_stone_brain), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_brain").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_stone_meet), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_meet").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_stone_virus), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_virus").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone"))));

            registration.addIngredientInfo(new ItemStack(init.nightmare_base_reversal_card), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_card").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_reversal_mysterious), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_mysterious").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_reversal_orb), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_orb").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal"))));

            registration.addIngredientInfo(new ItemStack(init.nightmare_base_redemption_deception), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_deception").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_redemption_degenerate), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_degenerate").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_redemption_down_and_out), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_down_and_out").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption"))));

            registration.addIngredientInfo(new ItemStack(init.nightmare_base_fool_betray), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_betray").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_fool_bone), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_bone").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_fool_soul), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_soul").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool"))));


            registration.addIngredientInfo(new ItemStack(init.nightmare_base_insight_collapse), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_collapse").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_insight_drug), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_drug").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_insight_insane), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_insane").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight"))));


            registration.addIngredientInfo(new ItemStack(init.nightmare_base_start_egg), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_egg").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_start_pod), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_pod").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start"))));
            registration.addIngredientInfo(new ItemStack(init.nightmare_base_start_power), VanillaTypes.ITEM_STACK, Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_power").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start"))));
        }
    }
}
