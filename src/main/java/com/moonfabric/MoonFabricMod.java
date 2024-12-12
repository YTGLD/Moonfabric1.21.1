package com.moonfabric;

import com.mojang.logging.LogUtils;
import com.moonfabric.Effects.initEffect;
import com.moonfabric.init.*;
import com.moonfabric.Ievent.evt.Aevent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;


public class MoonFabricMod implements ModInitializer {

	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String MODID = "moonfabric";

	public static final SimpleParticleType t  =FabricParticleTypes.simple();
	public static final SimpleParticleType  GOLD  =FabricParticleTypes.simple();
	public static final SimpleParticleType  FOLLOW  =FabricParticleTypes.simple();
	public static final SimpleParticleType  Origin  =FabricParticleTypes.simple();
	public static final SimpleParticleType  S  =FabricParticleTypes.simple();
	@Override
	public void onInitialize() {


		new Aevent();



		new AttReg();

		new Data();

		Registry.register(Registries.BLOCK, Identifier.of("moonfabric", "blockbloodorb"), init.blockbloodorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "blockbloodorb"), new BlockItem( init.blockbloodorb, new Item.Settings()));

		Registry.register(Registries.BLOCK, Identifier.of("moonfabric", "block_amout"), init.block_amout);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "block_amout"), new BlockItem( init.block_amout, new Item.Settings()));

		Registry.register(Registries.BLOCK, Identifier.of("moonfabric", "block_hand_black"), init.block_hand_black);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "block_hand_black"), new BlockItem( init.block_hand_black, new Item.Settings()));

		Registry.register(Registries.BLOCK, Identifier.of("moonfabric", "block_cube"), init.block_cube);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "block_cube"), new BlockItem( init.block_cube, new Item.Settings()));

		Registry.register(Registries.BLOCK, Identifier.of("moonfabric", "cursecandleblock"), init.cursecandleblock);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cursecandleblock"), new BlockItem( init.cursecandleblock, new Item.Settings()));

		Registry.register(Registries.PARTICLE_TYPE, Identifier.of("moonfabric", "blood"), t);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of("moonfabric", "gold"), GOLD);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of("moonfabric", "follow"), FOLLOW);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of("moonfabric", "origin"), Origin);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of("moonfabric", "sword"), S);
		net.fabricmc.fabric.api.loot.v3.LootTableEvents.MODIFY.register((key,builder,source,wrapperLookup)->{
			moonfabricLoot.onLootTableLoad(key,builder);
		});
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "moonstone"), init.Moonstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "goldheart"), init.goldheart);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "firehead"), init.firehead);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "grassstone"), init.grassstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "fallstone"), init.fallstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "waterstone"), init.waterstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "rageorb"), init.rageorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "rageapple"), init.rageapple);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doomfruit"), init.doomfruit);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doomstone"), init.doomstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doomsoul"), init.doomsoul);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cursecandle"), init.cursecandle);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "curseeye"), init.curseeye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doomcharm"), init.doomcharm);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doomtreasure"), init.doomtreasure);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "curseshield"), init.curseshield);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "redstone"), init.redstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bluestone"), init.bluestone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "greenstone"), init.greenstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "whiteorb"), init.whiteorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "blackorb"), init.blackorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "blackhead"), init.blackhead);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "goldbox"), init.goldbox);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "grail"), init.grail);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "candle"), init.candle);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "book"), init.book);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "twistedorb"), init.twistedorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "twistedsoul"), init.twistedsoul);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "twistedstone"), init.twistedstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cube"), init.cube);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "goldcottoncandy"), init.goldcottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "woodcottoncandy"), init.woodcottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "stonecottoncandy"), init.stonecottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "watercottoncandy"), init.watercottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "firecottoncandy"), init.firecottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "snail"), init.snail);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "origincube"), init.origincube);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "abook"), init.abook);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "gazer"), init.gazer);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bloodcharm"), init.bloodcharm);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "furybloodpearl"), init.furybloodpearl);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "glodstone"), init.glodstone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "fissionreactor"), init.fissionreactor);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bloodorb"), init.bloodorb);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nanoheart"), init.nanoheart);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nanofruit"), init.nanofruit);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nanoeye"), init.nanoeye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nanocube"), init.nanocube);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doom_a"), init.thedoomeye_ui);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "doom_b"), init.thedoomeye_ui1);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nanocottoncandy"), init.nanocottoncandy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bloodeye"), init.bloodeye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bloodtime"), init.bloodtime);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "sevensword"), init.sevensword);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_box_ui"), init.pain_box_ui);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_book"), init.pain_book);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_carrot"), init.pain_carrot);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "blood_amout"), init.blood_amout);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nightmareanchor"), init.nightmareanchor);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nightmarecharm"), init.nightmarecharm);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nightmareeye"), init.nightmareeye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nightmarerotten"), init.nightmarerotten);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "nightmarestone"), init.nightmarestone);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "blood_candle"), init.blood_candle);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "greedcrystal"), init.greedcrystal);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "mblock"), init.mblock);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "meye"), init.meye);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmapple"), init.ectoplasmapple);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmball"), init.ectoplasmball);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmbattery"), init.ectoplasmbattery);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmcloub"), init.ectoplasmcloub);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmcube"), init.ectoplasmcube);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmhorseshoe"), init.ectoplasmhorseshoe);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmprism"), init.ectoplasmprism);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmshild"), init.ectoplasmshild);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ectoplasmstar"), init.ectoplasmstar);


		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_box"), init.pain_box);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_heart"), init.pain_heart);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_ring"), init.pain_ring);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_stone"), init.pain_stone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_candle"), init.pain_candle);


		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "atp_height"), DNAItems.atp_height);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_acid"), DNAItems.cell_acid);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_big_boom"), DNAItems.cell_big_boom);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_bone_add"), DNAItems.cell_bone_add);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_break_down_water"), DNAItems.cell_break_down_water);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_chromosome"), DNAItems.cell_chromosome);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_compress"), DNAItems.cell_compress);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_constant"), DNAItems.cell_constant);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_cranial"), DNAItems.cell_cranial);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_darwin"), DNAItems.cell_darwin);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_digestion"), DNAItems.cell_digestion);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_disorder"), DNAItems.cell_disorder);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_dna_suppression"), DNAItems.cell_dna_suppression);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_eyes"), DNAItems.cell_eyes);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_flu"), DNAItems.cell_flu);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_god"), DNAItems.cell_god);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_ground"), DNAItems.cell_ground);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_in_air"), DNAItems.cell_in_air);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_in_water"), DNAItems.cell_in_water);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_inheritance"), DNAItems.cell_inheritance);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_necrosis"), DNAItems.cell_necrosis);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_off_on"), DNAItems.cell_off_on);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_oxygen"), DNAItems.cell_oxygen);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_preferential"), DNAItems.cell_preferential);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_putrefactive"), DNAItems.cell_putrefactive);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_sense"), DNAItems.cell_sense);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_synthesis"), DNAItems.cell_synthesis);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "speed_metabolism"), DNAItems.speed_metabolism);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "dna"), init.dna);




		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "ambush"), init.ambush);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "atpoverdose"), init.atpoverdose);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "autolytic"), init.autolytic);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "fermentation"), init.fermentation);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "putrefactive"), init.putrefactive);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "regenerative"), init.regenerative);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "necora"), init.necora);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "adrenaline"), init.adrenaline);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "anaerobic_cell"), init.anaerobic_cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "bone_cell"), init.bone_cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell"), init.cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_blood"), init.cell_blood);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_boom"), init.cell_boom);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_calcification"), init.cell_calcification);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "cell_mummy"), init.cell_mummy);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "disgusting_cells"), init.disgusting_cells);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "giant"), init.giant);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "giant_boom_cell"), init.giant_boom_cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "giant_nightmare"), init.giant_nightmare);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "mother_cell"), init.mother_cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "parasitic_cell"), init.parasitic_cell);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "subspace_cell"), init.subspace_cell);


		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "double_head"), init.double_head);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "death_penalty"), init.death_penalty);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "undead_head"), init.undead_head);

		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_eye"), init.owner_blood_eye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_attack_eye"), init.owner_blood_attack_eye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_speed_eye"), init.owner_blood_speed_eye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_effect_eye"), init.owner_blood_effect_eye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_boom_eye"), init.owner_blood_boom_eye);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_vex"), init.owner_blood_vex);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "owner_blood_earth"), init.owner_blood_earth);




		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "nightmare_giant"), InItEntity.nightmare_giant);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "cell_giant"), InItEntity.cell_giant);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "cell_zombie"), InItEntity.cell_zombie);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "nig_test"), InItEntity.nig_test);

		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "head"), InItEntity.head);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "penalty"), InItEntity.penalty);



		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "flysword"), InItEntity.Fly);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "line"), InItEntity.Line);

		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "owner_blood"), InItEntity.OWNER_BLOOD_ENTITY_TYPE);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "attack_blood"), InItEntity.ATTACK_BLOOD_ENTITY_TYPEttack_blood);


		Registry.register(Registries.ITEM_GROUP, Identifier.of("moonfabric", "moonfabric_tab"), aaa.Moon_Fabric_Tab);
		Registry.register(Registries.ITEM_GROUP, Identifier.of("moonfabric", "moonfabric_tab_dna"), aaa.DNA);

		Registry.register(Registries.STATUS_EFFECT, Identifier.of("moonfabric", "blood"), initEffect.blood);
	}
	public static RegistryEntry.Reference<StatusEffect> blood =Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("moonfabric", "blood"), initEffect.blood);

}