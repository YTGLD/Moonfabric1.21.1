package com.moonfabric;

import com.moonfabric.Effects.initEffect;
import com.moonfabric.init.*;
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


public class MoonFabricMod implements ModInitializer {

	public static final String MODID = "moonfabric";

	public static final SimpleParticleType t  =FabricParticleTypes.simple();
	public static final SimpleParticleType  GOLD  =FabricParticleTypes.simple();
	public static final SimpleParticleType  FOLLOW  =FabricParticleTypes.simple();
	public static final SimpleParticleType  Origin  =FabricParticleTypes.simple();
	public static final SimpleParticleType  S  =FabricParticleTypes.simple();

	@Override
	public void onInitialize() {


		new Aevent();



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



		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_box"), init.pain_box);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_heart"), init.pain_heart);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_ring"), init.pain_ring);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_stone"), init.pain_stone);
		Registry.register(Registries.ITEM, Identifier.of("moonfabric", "pain_candle"), init.pain_candle);

		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "flysword"), InItEntity.Fly);
		Registry.register(Registries.ENTITY_TYPE, Identifier.of("moonfabric", "line"), InItEntity.Line);


		Registry.register(Registries.ITEM_GROUP, Identifier.of("moonfabric", "moonfabric_tab"), aaa.Moon_Fabric_Tab);

		Registry.register(Registries.STATUS_EFFECT, Identifier.of("moonfabric", "blood"), initEffect.blood);
	}
	public static RegistryEntry.Reference<StatusEffect> blood =Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("moonfabric", "blood"), initEffect.blood);

}