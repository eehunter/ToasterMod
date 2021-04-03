package com.oyosite.ticon.toaster.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class ProtoTailModel extends BipedEntityModel<LivingEntity> {
    public final ModelPart tail;
    public final ModelPart cube_r3;
    public final ModelPart bone4;
    public final ModelPart cube_r4;
    public final ModelPart bone5;
    public final ModelPart cube_r5;
    public final ModelPart bone12;
    public ProtoTailModel() {
        super(i -> RenderLayer.getEntityTranslucent((Identifier) i), 0, 0.0F, 256, 256);
        super.setVisible(false);
        child = false;
        tail = new ModelPart(this);
        tail.setPivot(0.0F, 12.0F, 0.0F);
        cube_r3 = new ModelPart(this);
        cube_r3.setPivot(1.0F, 0.0F, 0.0F);
        tail.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.3054F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(202, 160).addCuboid(-3.0F, -4.0F, 0.0F, 4.0F, 4.0F, 6.0F, 0.2F, false);bone4 = new ModelPart(this);
        bone4.setPivot(0.0F, 0.0F, 0.0F);
        tail.addChild(bone4);
        this.torso = new ModelPart(this, 16, 16);
        this.torso.setPivot(0.0F, 0.0F, 0.0F);
        torso.addChild(tail);

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
    }
    public Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.torso);
    }
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}

