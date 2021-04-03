package com.oyosite.ticon.toaster.client.model;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;

public class ProtoLegModel extends PlayerEntityModel<LivingEntity> {

    public ProtoLegModel() {
        super(0, false);
        setVisible(false);
        this.child = false;
    }

    public void renderLeg(boolean right, MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        rightPantLeg.copyPositionAndRotation(rightLeg);
        leftPantLeg.copyPositionAndRotation(leftLeg);
        leftLeg.visible = !right;
        leftPantLeg.visible = !right;
        rightLeg.visible = right;
        rightPantLeg.visible = right;
        render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}