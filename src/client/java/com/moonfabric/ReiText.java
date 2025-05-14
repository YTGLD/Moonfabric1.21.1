package com.moonfabric;
//
//import com.moonfabric.init.init;
//import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
//import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
//import me.shedaniel.rei.api.common.util.EntryIngredients;
//import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
//import net.minecraft.text.Text;
//
//import java.util.List;
//
//public class ReiText implements REIClientPlugin {
//    public void registerDisplays(DisplayRegistry registration) {
//        {
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.twistedstone)), Text.translatable("moonfabric.jei.twistedstone")));
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ambush)), Text.translatable("item.moonfabric.ambush").append(Text.translatable("moonfabric.jei.necora.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.atpoverdose)), Text.translatable("item.moonfabric.atpoverdose").append(Text.translatable("moonfabric.jei.necora.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.autolytic)), Text.translatable("item.moonfabric.autolytic").append(Text.translatable("moonfabric.jei.necora.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.fermentation)), Text.translatable("item.moonfabric.fermentation").append(Text.translatable("moonfabric.jei.necora.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.putrefactive)), Text.translatable("item.moonfabric.putrefactive").append(Text.translatable("moonfabric.jei.necora.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.regenerative)), Text.translatable("item.moonfabric.regenerative").append(Text.translatable("moonfabric.jei.necora.all"))));
//
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.anaerobic_cell)), Text.translatable("item.moonfabric.anaerobic_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.giant_boom_cell)), Text.translatable("item.moonfabric.giant_boom_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.subspace_cell)), Text.translatable("item.moonfabric.subspace_cell").append(Text.translatable("moonfabric.jei.necora.giant_nightmare.all"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.cell_mummy)), Text.translatable("item.moonfabric.adrenaline").append(Text.translatable("moonfabric.jei.cell.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.cell_boom)), Text.translatable("item.moonfabric.cell_mummy").append(Text.translatable("moonfabric.jei.cell.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.cell_calcification)), Text.translatable("item.moonfabric.cell_boom").append(Text.translatable("moonfabric.jei.cell.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.cell_blood)), Text.translatable("item.moonfabric.cell_blood").append(Text.translatable("moonfabric.jei.cell.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.adrenaline)), Text.translatable("item.moonfabric.cell_calcification").append(Text.translatable("moonfabric.jei.cell.all"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmareanchor)), Text.translatable("item.moonfabric.nightmareanchor").append(Text.translatable("moonfabric.jei.nightmare"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmarecharm)), Text.translatable("item.moonfabric.nightmarecharm").append(Text.translatable("moonfabric.jei.nightmare"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmareeye)), Text.translatable("item.moonfabric.nightmareeye").append(Text.translatable("moonfabric.jei.nightmare"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmarerotten)), Text.translatable("item.moonfabric.nightmarerotten").append(Text.translatable("moonfabric.jei.nightmare"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmarestone)), Text.translatable("item.moonfabric.nightmarestone").append(Text.translatable("moonfabric.jei.nightmare"))));
//
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.mblock)), Text.translatable("item.moonfabric.mblock").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.maulice.all"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.meye)), Text.translatable("item.moonfabric.meye").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.maulice.all"))));
//
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmball)), Text.translatable("item.moonfabric.ectoplasmball").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.ectoplasmball"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmcloub)), Text.translatable("item.moonfabric.ectoplasmcloub").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.ectoplasmcloub"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmshild)), Text.translatable("item.moonfabric.ectoplasmshild").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmapple)), Text.translatable("item.moonfabric.ectoplasmapple").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmprism)), Text.translatable("item.moonfabric.ectoplasmprism").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmcube)), Text.translatable("item.moonfabric.ectoplasmcube").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmstar)), Text.translatable("item.moonfabric.ectoplasmstar").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.ectoplasm.all"))));
//
//
//
//
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.ectoplasmhorseshoe)), Text.translatable("item.moonfabric.ectoplasmhorseshoe").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all"))));
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.greedcrystal)), Text.translatable("item.moonfabric.greedcrystal").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.whiteorb)), Text.translatable("item.moonfabric.whiteorb").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.loot.all"))));
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nanocube)), Text.translatable("item.moonfabric.nanocube").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.nanodoom.all"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.calcification)), Text.translatable("item.moonfabric.calcification").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.calcification"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.masticatory)), Text.translatable("item.moonfabric.masticatory").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.masticatory"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.polyphagia)), Text.translatable("item.moonfabric.polyphagia").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.polyphagia"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.quadriceps)), Text.translatable("item.moonfabric.quadriceps").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.quadriceps"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.reanimation)), Text.translatable("item.moonfabric.reanimation").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.reanimation"))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.blood_amout)), Text.translatable("item.moonfabric.blood_amout").append(Text.literal(" : ")).append(Text.translatable("moonfabric.jei.blood.all"))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_attack_eye)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_speed_eye)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_eye)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_effect_eye)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_boom_eye)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_vex)),  Text.translatable("moonfabric.jei.blood.all")));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.owner_blood_earth)),  Text.translatable("moonfabric.jei.blood.all")));
//
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_black_eye_heart)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_heart").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_black_eye_eye)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_eye").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_black_eye_red)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_black_eye_red").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_black_eye")))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_stone_brain)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_brain").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_stone_meet)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_meet").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_stone_virus)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_stone_virus").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_stone")))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_reversal_card)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_card").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_reversal_mysterious)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_mysterious").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_reversal_orb)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_reversal_orb").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_reversal")))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_redemption_deception)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_deception").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_redemption_degenerate)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_degenerate").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_redemption_down_and_out)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_redemption_down_and_out").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_redemption")))));
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_fool_betray)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_betray").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_fool_bone)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_bone").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_fool_soul)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_fool_soul").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_fool")))));
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_insight_collapse)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_collapse").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_insight_drug)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_drug").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_insight_insane)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_insight_insane").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_insight")))));
//
//
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_start_egg)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_egg").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_start_pod)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_pod").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start")))));
//            registration.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(init.nightmare_base_start_power)), Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base_start_power").append(Text.translatable("moonfabric.jei.item.moonfabric.nightmare_base.all").append(Text.translatable("item.moonfabric.nightmare_base_start")))));
//        }
//
//
//
//
//    }
//}
