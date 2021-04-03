package com.oyosite.ticon.toaster.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class ProtoMainModel extends BipedEntityModel{ //private final ModelPart tail;
    private List<ModelPart> parts = Lists.newArrayList();
    public final ModelPart leftSleeve;
    public final ModelPart rightSleeve;
    public final ModelPart leftPantLeg;
    public final ModelPart rightPantLeg;
    public final ModelPart jacket;
    public final ModelPart protoHead;
    public final ModelPart bone17;
    public final ModelPart cube_r6;
    public final ModelPart bone16;
    public final ModelPart cube_r7;
    public final ModelPart bone15;
    public final ModelPart bone13;
    public final ModelPart bone14;
    public final ModelPart cube_r8;
    public final ModelPart tail;
    public final ModelPart cube_r3;
    public final ModelPart bone4;
    public final ModelPart cube_r4;
    public final ModelPart bone5;
    public final ModelPart cube_r5;
    public final ModelPart bone12;
    public ProtoMainModel() {
        super(i -> RenderLayer.getEntityTranslucent((Identifier) i), 0, 0.0F, 256, 256);
        this.child = false;
        leftSleeve = new ModelPart(this, 160, 240);
        rightSleeve = new ModelPart(this, 192, 240);
        leftPantLeg = new ModelPart(this, 0, 32);
        rightPantLeg = new ModelPart(this, 0, 32);

        leftLeg.mirror = true;
        leftPantLeg.mirror = true;

        tail = new ModelPart(this);
        tail.setPivot(0.0F, 12.0F, 0.0F);
        cube_r3 = new ModelPart(this);
        cube_r3.setPivot(1.0F, 0.0F, 0.0F);
        tail.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.3054F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(202, 160).addCuboid(-3.0F, -4.0F, 0.0F, 4.0F, 4.0F, 6.0F, 0.2F, false);bone4 = new ModelPart(this);
        bone4.setPivot(0.0F, 0.0F, 0.0F);
        tail.addChild(bone4);

        cube_r4 = new ModelPart(this);
        cube_r4.setPivot(0.0F, 0.0F, 5.0F);
        bone4.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.2182F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(152, 170).addCuboid(-2.0F, -2.4965F, 1.2314F, 4.0F, 4.0F, 6.0F, 0.0F, false);

        bone5 = new ModelPart(this);
        bone5.setPivot(0.0F, 0.0F, 0.0F);
        bone4.addChild(bone5);


        cube_r5 = new ModelPart(this);
        cube_r5.setPivot(0.0F, 2.0F, 12.0F);
        bone5.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.1309F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(152, 160).addCuboid(-2.0F, -2.934F, -1.0355F, 4.0F, 4.0F, 6.0F, -0.2F, false);

        bone12 = new ModelPart(this);
        bone12.setPivot(0.0F, 0.0F, 0.0F);
        bone5.addChild(bone12);
        bone12.setTextureOffset(202, 170).addCuboid(-2.0F, -0.375F, 15.9356F, 4.0F, 4.0F, 6.0F, -0.4F, false);

        this.torso = new ModelPart(this, 73, 240);
        this.torso.addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F);
        this.torso.setPivot(0.0F, 0.0F, 0.0F);

        this.jacket = new ModelPart(this, 97, 240);
        this.jacket.addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F);
        this.jacket.setPivot(0.0F, 0.0F, 0.0F);

        this.leftArm = new ModelPart(this, 176, 240);
        this.leftArm.addCuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        this.leftArm.setPivot(5.0F, 2.0F, 0.0F);
        this.rightArm = new ModelPart(this, 208, 240);
        this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        this.rightArm.setPivot(-5.0F, 2.0F, 0.0F);
        this.leftSleeve.addCuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F);
        this.leftSleeve.setPivot(5.0F, 2.0F, 0.0F);
        this.rightSleeve.addCuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F);
        this.rightSleeve.setPivot(-5.0F, 2.0F, 10.0F);

        this.leftPantLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F);
        this.leftPantLeg.setPivot(1.9F, 12.0F, 0.0F);
        this.rightPantLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F);
        this.rightPantLeg.setPivot(-1.9F, 12.0F, 0.0F);

        protoHead = new ModelPart(this);
        protoHead.setPivot(head.pivotX, head.pivotY, head.pivotZ);
        //head.addChild(protoHead);
        head.visible = false;
        protoHead.setTextureOffset(0, 230).addCuboid(-2.0F, -5.0F, -6.0F, 4.0F, 5.0F, 8.0F, 0.0F, false);
        ModelPartIlluminated faceFront = new ModelPartIlluminated(this);
        faceFront.setTextureOffset(98, 182).addCuboid(-5.5F, -12.0F, -13.5F, 11.0F, 19.0F, 15.0F, -7.6F, false);
        protoHead.addChild(faceFront);

        bone17 = new ModelPart(this);
        bone17.setPivot(-1.0F, 0.0F, 0.0F);
        protoHead.addChild(bone17);
        setRotationAngle(bone17, 0.1309F, 0.0F, 0.0F);


        cube_r6 = new ModelPart(this);
        cube_r6.setPivot(0.0F, 0.0F, 0.0F);
        bone17.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, -0.4363F, 0.0F);
        cube_r6.setTextureOffset(50, 249).addCuboid(-1.0F, -3.0F, 7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r6.setTextureOffset(46, 246).addCuboid(-1.0F, -2.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r6.setTextureOffset(46, 249).addCuboid(-1.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r6.setTextureOffset(36, 243).addCuboid(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);

        bone16 = new ModelPart(this);
        bone16.setPivot(1.0F, 0.0F, 0.0F);
        protoHead.addChild(bone16);
        setRotationAngle(bone16, 0.1309F, 0.0F, 0.0F);


        cube_r7 = new ModelPart(this);
        cube_r7.setPivot(0.0F, 0.0F, 0.0F);
        bone16.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.4363F, 0.0F);
        cube_r7.setTextureOffset(54, 249).addCuboid(0.0F, -3.0F, 7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r7.setTextureOffset(56, 246).addCuboid(0.0F, -2.0F, 5.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        cube_r7.setTextureOffset(58, 249).addCuboid(0.0F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r7.setTextureOffset(54, 243).addCuboid(0.0F, -5.0F, 0.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);

        bone15 = new ModelPart(this);
        bone15.setPivot(2.25F, -2.0F, -6.0F);
        protoHead.addChild(bone15);
        setRotationAngle(bone15, 0.0F, 0.0873F, 0.0F);
        bone15.setTextureOffset(0, 243).addCuboid(-1.0F, -3.0F, 0.0F, 1.0F, 5.0F, 8.0F, -0.1F, false);
        ModelPartIlluminated faceSide1 = new ModelPartIlluminated(this);
        faceSide1.setTextureOffset(150, 199).addCuboid(-7.6F, -10.0F, -7.6F, 15.0F, 19.0F, 22.0F, -7.6F, false);
        bone15.addChild(faceSide1);

        bone13 = new ModelPart(this);
        bone13.setPivot(0.0F, -5.1F, -6.0F);
        protoHead.addChild(bone13);
        setRotationAngle(bone13, 0.0873F, 0.0F, 0.0F);
        bone13.setTextureOffset(0, 221).addCuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 8.0F, -0.1F, false);

        bone14 = new ModelPart(this);
        bone14.setPivot(0.0F, 0.0F, 0.0F);
        protoHead.addChild(bone14);


        cube_r8 = new ModelPart(this);
        cube_r8.setPivot(-2.25F, -2.0F, -6.0F);
        bone14.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, -0.0873F, 0.0F);
        ModelPartIlluminated faceSide2 = new ModelPartIlluminated(this);
        faceSide2.setTextureOffset(150, 158).addCuboid(-7.4F, -10.0F, -7.6F, 15.0F, 19.0F, 22.0F, -7.6F, false);
        cube_r8.addChild(faceSide2);
        cube_r8.setTextureOffset(18, 243).addCuboid(0.0F, -3.0F, 0.0F, 1.0F, 5.0F, 8.0F, -0.1F, false);
        torso.addChild(tail);
    }

    /*public void showLimbs(){
        setRightArmVisible(true);
        setLeftArmVisible(true);
        setRightLegVisible(true);
        setLeftLegVisible(true);
        setTailVisible(true);
    }

    public void setHeadVisible(boolean visible){
        protoHead.visible = visible;
        bone17.visible = visible;
        cube_r6.visible = visible;
        bone16.visible = visible;
        cube_r7.visible = visible;
        bone15.visible = visible;
        bone13.visible = visible;
        bone14.visible = visible;
        cube_r8.visible = visible;
    }*/

    public void setRightArmVisible(boolean visible){
        rightArm.visible = visible;
        rightSleeve.visible = visible;
    }

    public void setLeftArmVisible(boolean visible){
        leftArm.visible = visible;
        leftSleeve.visible = visible;
    }

    public void setRightLegVisible(boolean visible){
        rightLeg.visible = visible;
        rightPantLeg.visible = visible;
    }

    public void setLeftLegVisible(boolean visible){
        leftLeg.visible = visible;
        leftPantLeg.visible = visible;
    }

    public void setTailVisible(boolean visible) {
        tail.visible = visible;
        cube_r3.visible = visible;
        bone4.visible = visible;
        cube_r4.visible = visible;
        bone5.visible = visible;
        cube_r5.visible = visible;
        bone12.visible = visible;
    }

    public Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.protoHead);//, this.bone17, this.cube_r6, this.bone16, this.cube_r7, this.bone15, this.bone13, this.bone14, this.cube_r8);
    }

    // I don't think this needs to be checked.
    @SuppressWarnings("unchecked")
    public Iterable<ModelPart> getBodyParts() {
        return (Iterable<ModelPart>) Iterables.concat(super.getBodyParts(), ImmutableList.of(this.leftPantLeg, this.rightPantLeg, this.leftSleeve, this.rightSleeve, this.jacket));
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        setTailVisible(visible);
        leftSleeve.visible = visible;
        rightSleeve.visible = visible;
        leftPantLeg.visible = visible;
        rightPantLeg.visible = visible;
        protoHead.visible = visible;
        bone17.visible = visible;
        cube_r6.visible = visible;
        bone16.visible = visible;
        cube_r7.visible = visible;
        bone15.visible = visible;
        bone13.visible = visible;
        bone14.visible = visible;
        cube_r8.visible = visible;
        head.visible = false;
        helmet.visible = false;
    }
}
