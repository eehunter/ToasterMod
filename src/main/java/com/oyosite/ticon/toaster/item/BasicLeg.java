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
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class BasicLeg extends TrinketItem implements ProtoLimbItem {
    public BipedEntityModel<LivingEntity> model;
    private Identifier texture = new Identifier("toastermod", "textures/entity/leg_base.png");
    private Identifier overlay;
    public BasicLeg(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        if (player.isInvisible()) return;
        getModel().leftLeg.copyPositionAndRotation(model.leftLeg);
        getModel().rightLeg.copyPositionAndRotation(model.rightLeg);
        int j = ProtoLimbItem.getColor(player, slot); //EntityEntrypoint.PROTO_COMP.get(player).getColor();
        //ItemStack stack = TrinketsApi.getTrinketComponent(player).getInventory().getStack(TrinketSlots.getAllSlotNames().indexOf(slot));
        //if (stack.getItem() instanceof ProtoLimbItem){ if (((ProtoLimbItem) stack.getItem()).hasColor(stack)) j = ((ProtoLimbItem) stack.getItem()).getColor(stack); }
        float f = (float)(j >> 16 & 255) / 255.0F;
        float g = (float)(j >> 8 & 255) / 255.0F;
        float h = (float)(j & 255) / 255.0F;
        VertexConsumer vertexBuilder1 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getTexture()), false, false);
        this.getModel().renderLeg(slot.endsWith("rightleg"), matrixStack, vertexBuilder1, light, OverlayTexture.DEFAULT_UV, f, g, h, 1);
        VertexConsumer vertexBuilder2 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getOverlay()), false, false);
        this.getModel().renderLeg(slot.endsWith("rightleg"), matrixStack, vertexBuilder2, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    public BasicLeg setModel(BipedEntityModel<LivingEntity> model){
        this.model = model;
        return this;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ProtoLegModel getModel() {
        return (ProtoLegModel) model;
    }


    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.LEGS)&&(slot.equals(ToasterMod.LEFT_LEG)||slot.equals(ToasterMod.RIGHT_LEG));
    }

    @Environment(EnvType.CLIENT)
    public Identifier getTexture(){ return this.texture; }

    @Environment(EnvType.CLIENT)
    public BasicLeg setTexture(Identifier texture){
        this.texture = texture;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public BasicLeg setOverlay(Identifier overlay){
        this.overlay = overlay;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getOverlay(){
        return this.overlay;
    }
}
