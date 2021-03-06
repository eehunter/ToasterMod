package com.oyosite.ticon.toaster.item;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.client.model.ProtoArmModel;
import com.oyosite.ticon.toaster.client.model.ProtoLegModel;
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
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class EffectLeg extends TrinketItem implements EffectLimb {
    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    private Identifier texture;
    private Identifier overlay;
    private StatusEffect effect;
    private int amp = 0;
    public EffectLeg(Settings settings) {
        super(settings.maxCount(1));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        if (player.isInvisible()) return;
        getModel().leftLeg.copyPositionAndRotation(model.leftLeg);
        getModel().rightLeg.copyPositionAndRotation(model.rightLeg);
        int j = ProtoLimbItem.getColor(player, slot);
        float f = (float)(j >> 16 & 255) / 255.0F;
        float g = (float)(j >> 8 & 255) / 255.0F;
        float h = (float)(j & 255) / 255.0F;
        VertexConsumer vertexBuilder1 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getTexture()), false, false);
        this.getModel().renderLeg(slot.endsWith("rightleg"), matrixStack, vertexBuilder1, light, OverlayTexture.DEFAULT_UV, f, g, h, 1);
        VertexConsumer vertexBuilder2 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getOverlay()), false, false);
        this.getModel().renderLeg(slot.endsWith("rightleg"), matrixStack, vertexBuilder2, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    public EffectLeg setAmplifier(int amp){
        this.amp = amp;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public EffectLeg setModel(BipedEntityModel<LivingEntity> model){
        this.model = model;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public EffectLeg setOverlay(Identifier overlay){
        this.overlay = overlay;
        return this;
    }

    public EffectLeg setEffect(StatusEffect effect){
        this.effect = effect;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public EffectLeg setTexture(Identifier texture){
        this.texture = texture;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getTexture(){ return this.texture; }

    @Override
    @Environment(EnvType.CLIENT)
    public ProtoLegModel getModel() {
        return (ProtoLegModel) model;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getOverlay(){
        return this.overlay;
    }

    @Override
    public StatusEffectInstance getEffectToApply() {
        return new StatusEffectInstance(this.effect, 40, amp, true, false);
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.LEGS)&&(slot.equals(ToasterMod.LEFT_LEG)||slot.equals(ToasterMod.RIGHT_LEG));
    }
}
