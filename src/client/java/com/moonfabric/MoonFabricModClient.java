package com.moonfabric;

import com.moonfabric.EntiyMl.AttackBloodRender;
import com.moonfabric.EntiyMl.FlyEntityRenderer;
import com.moonfabric.EntiyMl.LineRender;
import com.moonfabric.EntiyMl.OwnerBloodRender;
import com.moonfabric.PAT.*;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import com.moonfabric.test.Black.BlockHandR;
import com.moonfabric.test.Blood.BloodR;
import com.moonfabric.test.BloodOrb.BloodOrbR;
import com.moonfabric.test.Box.ChestR;
import com.moonfabric.test.ORB.blackorbR;
import com.moonfabric.test.ORB.orbR;
import com.moonfabric.test.cubeR;
import com.moonfabric.test.cursecandle.cursecandleR;
import com.moonfabric.test.o.oR;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class MoonFabricModClient implements ClientModInitializer {
	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {


		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.Fly ,FlyEntityRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.Line , LineRender::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.ATTACK_BLOOD_ENTITY_TYPEttack_blood , AttackBloodRender::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.OWNER_BLOOD_ENTITY_TYPE , OwnerBloodRender::new);



		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.GOLD, Gold.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.FOLLOW, Follow.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.t, Blood.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.Origin, Origin.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.S, sword.CloudFactory::new);


		TrinketRendererRegistry.registerRenderer(init.cube, new cubeR());
		TrinketRendererRegistry.registerRenderer(init.twistedstone, new ChestR());
		TrinketRendererRegistry.registerRenderer(init.blackhead, new BlockHandR());
		TrinketRendererRegistry.registerRenderer(init.blackorb, new blackorbR());
		TrinketRendererRegistry.registerRenderer(init.bloodorb, new BloodOrbR());

		TrinketRendererRegistry.registerRenderer(init.whiteorb, new orbR());
		TrinketRendererRegistry.registerRenderer(init.cursecandle, new cursecandleR());
		TrinketRendererRegistry.registerRenderer(init.origincube, new oR());
		TrinketRendererRegistry.registerRenderer(init.furybloodpearl, new BloodR());

		CoreShaderRegistrationCallback.EVENT.register(Identifier.of(MoonFabricMod.MODID,"blood"),
				(context)->{
					context.register(Identifier.of(MoonFabricMod.MODID,"blood"), VertexFormat.builder().build(), MRender::setRenderTypeEndPortalProgram);
				});

	}
}