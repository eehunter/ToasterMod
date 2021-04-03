package com.oyosite.ticon.toaster.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ModelPartIlluminated extends ModelPart {
    public ModelPartIlluminated(Model model) {
        super(model);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        super.render(matrices, vertices, 15728640, overlay, red, green, blue, alpha);
    }


}
