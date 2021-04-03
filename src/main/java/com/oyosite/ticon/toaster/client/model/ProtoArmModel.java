package com.oyosite.ticon.toaster.client.model;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;

public class ProtoArmModel extends PlayerEntityModel<LivingEntity> {

    public ProtoArmModel() {
        super(0, false);
        setVisible(false);
        this.child = false;
    }

    public void renderHand(boolean mainHand, MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (MinecraftClient.getInstance().options.mainArm == Arm.LEFT)  mainHand = !mainHand;
        rightSleeve.copyPositionAndRotation(rightArm);
        leftSleeve.copyPositionAndRotation(leftArm);
        leftArm.visible = !mainHand;
        leftSleeve.visible = !mainHand;
        rightArm.visible = mainHand;
        rightSleeve.visible = mainHand;
        render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}