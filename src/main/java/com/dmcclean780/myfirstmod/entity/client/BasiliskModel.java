package com.dmcclean780.myfirstmod.entity.client;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.entity.custom.BasiliskEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;


public class BasiliskModel<T extends BasiliskEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = 
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID, "basilisk"), "main");

	private final ModelPart chicken;
	private final ModelPart leg1;
	private final ModelPart foot1;
	private final ModelPart leg2;
	private final ModelPart foot2;
	private final ModelPart wing1;
	private final ModelPart wing2;
	private final ModelPart head;
	private final ModelPart frill;
	private final ModelPart frill_top;
	private final ModelPart tail;
	private final ModelPart tail_start;
	private final ModelPart tail_middle;
	private final ModelPart tail_middle_end;
	private final ModelPart tail_end;

	public BasiliskModel(ModelPart root) {
		this.chicken = root.getChild("chicken");
		this.leg1 = this.chicken.getChild("leg1");
		this.foot1 = this.leg1.getChild("foot1");
		this.leg2 = this.chicken.getChild("leg2");
		this.foot2 = this.leg2.getChild("foot2");
		this.wing1 = this.chicken.getChild("wing1");
		this.wing2 = this.chicken.getChild("wing2");
		this.head = this.chicken.getChild("head");
		this.frill = this.chicken.getChild("frill");
		this.frill_top = this.frill.getChild("frill_top");
		this.tail = this.chicken.getChild("tail");
		this.tail_start = this.tail.getChild("tail_start");
		this.tail_middle = this.tail.getChild("tail_middle");
		this.tail_middle_end = this.tail.getChild("tail_middle_end");
		this.tail_end = this.tail.getChild("tail_end");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chicken = partdefinition.addOrReplaceChild("chicken", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -11.0F, -2.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition leg1 = chicken.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(28, 5).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -5.0F, 0.0F));

		PartDefinition foot1 = leg1.addOrReplaceChild("foot1", CubeListBuilder.create().texOffs(11, 18).addBox(-2.0F, 0.0F, 1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(13, 14).addBox(0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, -3.0F));

		PartDefinition leg2 = chicken.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(30, 5).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -5.0F, 3.0F));

		PartDefinition foot2 = leg2.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(11, 21).addBox(-2.0F, 0.0F, 1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(13, 15).addBox(0.0F, 0.0F, 2.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, -3.0F));

		PartDefinition wing1 = chicken.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, 0.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-2.0F, 4.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 12).addBox(-2.0F, 0.0F, -0.001F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -11.0F, -2.0F));

		PartDefinition wing2 = chicken.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-2.0F, 4.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 12).addBox(-2.0F, 0.0F, 0.001F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -11.0F, 4.0F));

		PartDefinition head = chicken.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -4.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 12).addBox(-4.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-3.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -11.0F, 1.0F));

		PartDefinition frill = chicken.addOrReplaceChild("frill", CubeListBuilder.create().texOffs(16, 12).addBox(1.0F, -6.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(16, 13).addBox(3.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 15).addBox(3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(28, 0).addBox(1.0F, -5.0F, 0.0F, 2.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -11.0F, 1.0F));

		PartDefinition frill_top = frill.addOrReplaceChild("frill_top", CubeListBuilder.create().texOffs(10, 12).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition tail = chicken.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition tail_start = tail.addOrReplaceChild("tail_start", CubeListBuilder.create().texOffs(2, 22).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 1.0F));

		PartDefinition tail_middle = tail.addOrReplaceChild("tail_middle", CubeListBuilder.create().texOffs(14, 24).addBox(2.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 1.0F));

		PartDefinition tail_middle_end = tail.addOrReplaceChild("tail_middle_end", CubeListBuilder.create().texOffs(22, 20).addBox(6.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 1.0F));

		PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(26, 30).addBox(9.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
    public void setupAnim(BasiliskEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(BasiliskAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, BasiliskAnimation.look_left, ageInTicks, 1f);
		this.animate(entity.idleAnimationState, BasiliskAnimation.look_right, ageInTicks, 1f);
	}

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        chicken.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return chicken;
    }
}