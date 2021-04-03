package com.oyosite.ticon.toaster.client;

import com.oyosite.ticon.toaster.entity.NaniteSlimeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NaniteSlimeRenderer extends MobEntityRenderer<NaniteSlimeEntity, SlimeEntityModel<NaniteSlimeEntity>> {
    private static final Identifier TEX = new Identifier("toastermod", "textures/entity/nanite_slime.png");

    public NaniteSlimeRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SlimeEntityModel<>(16), .25F);
        this.addFeature(new SlimeOverlayFeatureRenderer<>(this));
    }

    public void render(NaniteSlimeEntity slimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25F * (float)slimeEntity.getSize();
        super.render(slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(NaniteSlimeEntity entity) {
        return TEX;
    }
}
