package com.oyosite.ticon.toaster.item;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.client.model.ProtoArmModel;
import com.oyosite.ticon.toaster.client.model.ProtoTailModelWide;
import com.oyosite.ticon.toaster.component.EntityEntrypoint;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.List;

public class LeopardGeckoTail extends TrinketItem implements EffectLimb {
    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    private Identifier texture = new Identifier("toastermod", "textures/entity/leopard_gecko_tail_base.png");
    private Identifier overlay = new Identifier("toastermod", "textures/entity/leopard_gecko_tail_overlay.png");
    public LeopardGeckoTail(Settings settings) {
        super(settings.maxCount(1));
    }
    @Environment(EnvType.CLIENT)
    public LeopardGeckoTail setModel(BipedEntityModel<LivingEntity> model){
        this.model = model;
        return this;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ProtoTailModelWide getModel() {
        return (ProtoTailModelWide) model;
    }

    @Environment(EnvType.CLIENT)
    public LeopardGeckoTail setTexture(Identifier texture){
        this.texture = texture;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getTexture(){ return this.texture; }

    @Environment(EnvType.CLIENT)
    public LeopardGeckoTail setOverlay(Identifier overlay){
        this.overlay = overlay;
        return this;
    }

    @Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        if (player.isInvisible()) return;
        //getModel().torso.copyPositionAndRotation(model.torso);
        getModel().torso.copyPositionAndRotation(model.torso);
        int j = ProtoLimbItem.getColor(player, slot);
        float f = (float)(j >> 16 & 255) / 255.0F;
        float g = (float)(j >> 8 & 255) / 255.0F;
        float h = (float)(j & 255) / 255.0F;
        VertexConsumer vertexBuilder1 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getTexture()), false, false);
        this.getModel().render(matrixStack, vertexBuilder1, light, OverlayTexture.DEFAULT_UV, f, g, h, 1);
        VertexConsumer vertexBuilder2 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getOverlay()), false, false);
        this.getModel().render(matrixStack, vertexBuilder2, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.LEGS)&&(slot.equals(ToasterMod.TAIL));
    }

    @Environment(EnvType.CLIENT)
    public Identifier getOverlay(){
        return this.overlay;
    }

    @Override
    public StatusEffectInstance getEffectToApply() {
        return new StatusEffectInstance(StatusEffects.SATURATION, 40, 0, true, false);
    }
}
