package com.dmcclean780.myfirstmod.entity.client;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.entity.custom.BasiliskEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BasiliskRenderer extends MobRenderer<BasiliskEntity, BasiliskModel<BasiliskEntity>> {
    
    public BasiliskRenderer(EntityRendererProvider.Context context) {
        super(context, new BasiliskModel<>(context.bakeLayer(BasiliskModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(BasiliskEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID, "textures/entity/basilisk/basilisk.png");
    }

    @Override
    public void render(BasiliskEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
