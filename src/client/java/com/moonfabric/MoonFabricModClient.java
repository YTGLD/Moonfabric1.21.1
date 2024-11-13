package com.moonfabric;

import com.moonfabric.EntiyMl.*;
import com.moonfabric.EntiyMl.nig.NigEntityRenderer;
import com.moonfabric.EntiyMl.nig.NigRenderer;
import com.moonfabric.PAT.*;
import com.moonfabric.init.InItEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.impl.client.screen.ScreenEventFactory;
import net.minecraft.client.render.VertexFormat;
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
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.cell_zombie , ZombieEntityRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.cell_giant , GiantEntityRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.nightmare_giant , NigEntityRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.nig_test , NigRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.head , HeadAttackRenderer::new);
		net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register(InItEntity.penalty , PenaltyRenderer::new);



		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.GOLD, Gold.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.FOLLOW, Follow.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.t, Blood.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.Origin, Origin.CloudFactory::new);
		ParticleFactoryRegistry.getInstance().register(MoonFabricMod.S, sword.CloudFactory::new);

		CoreShaderRegistrationCallback.EVENT.register(Identifier.of(MoonFabricMod.MODID,"blood"),
				(context)->{
					context.register(Identifier.of(MoonFabricMod.MODID,"blood"), VertexFormat.builder().build(), MRender::setRenderTypeEndPortalProgram);
				});

	}
}