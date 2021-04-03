package com.oyosite.ticon.toaster.client;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.client.model.ProtoMainModel;
import com.oyosite.ticon.toaster.component.EntityEntrypoint;
import com.oyosite.ticon.toaster.item.ProtoLimbItem;
import dev.emi.trinkets.api.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.List;

public class ProtoFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context;
    private ProtoMainModel model;
    private final Identifier defaultTexture;
    private final Identifier overlayTexture;

    public ProtoFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
        this.context = context;
        this.defaultTexture = new Identifier("toastermod", "textures/entity/defaultprototexture.png");
        this.overlayTexture = new Identifier("toastermod", "textures/entity/defaultprototextureoverlay.png");
    }

    @Environment(EnvType.CLIENT)
    protected ProtoMainModel getModel() {
        if (this.model == null) {this.model = this.createModel();}
        return this.model;
    }

    @Environment(EnvType.CLIENT)
    protected ProtoMainModel createModel() {
        return new ProtoMainModel();
    }

    protected Identifier getTexture() {
        return this.defaultTexture;
    }
    protected Identifier getTextureOverlay() { return this.overlayTexture; }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexProvider, int light, AbstractClientPlayerEntity player, float a, float b, float c, float d, float headYaw, float headPitch) {
        if (player.isInvisible()) return;
        TrinketComponent comp = TrinketsApi.getTrinketComponent(player);//List<String> names = TrinketSlots.getAllSlotNames();
        List<String> names = TrinketSlots.getAllSlotNames();
        getModel().setVisible(true);
        for (int i = 0; i < comp.getInventory().size(); i++) {
            ItemStack stack = comp.getInventory().getStack(i);
            String slot = names.get(i).split(":")[1];
            if (stack.getItem() instanceof ProtoLimbItem){
                if (slot.equals("leftarm")) { getModel().setLeftArmVisible(false); }
                if (slot.equals("leftleg")) { getModel().setLeftLegVisible(false); }
                if (slot.equals("rightarm")) { getModel().setRightArmVisible(false); }
                if (slot.equals("rightleg")) { getModel().setRightLegVisible(false); }
                if (slot.equals("tail")) { getModel().setTailVisible(false); }
            }
        }
        model.torso.copyPositionAndRotation(context.getModel().torso);
        model.jacket.copyPositionAndRotation(context.getModel().jacket);
        model.rightArm.copyPositionAndRotation(context.getModel().rightArm);
        model.leftArm.copyPositionAndRotation(context.getModel().leftArm);
        model.rightSleeve.copyPositionAndRotation(context.getModel().rightSleeve);
        model.leftSleeve.copyPositionAndRotation(context.getModel().leftSleeve);
        model.rightLeg.copyPositionAndRotation(context.getModel().rightLeg);
        model.leftLeg.copyPositionAndRotation(context.getModel().leftLeg);
        model.rightPantLeg.copyPositionAndRotation(context.getModel().rightPantLeg);
        model.leftPantLeg.copyPositionAndRotation(context.getModel().leftPantLeg);
        model.protoHead.copyPositionAndRotation(context.getModel().head);

        int j = EntityEntrypoint.PROTO_COMP.get(player).getColor();
        ItemStack stack = TrinketsApi.getTrinketComponent(player).getInventory().getStack(TrinketSlots.getAllSlotNames().indexOf(SlotGroups.HEAD+":"+ ToasterMod.COLORIZER));
        if (stack.getItem() instanceof DyeableItem){ if (((DyeableItem) stack.getItem()).hasColor(stack)) j = ((DyeableItem) stack.getItem()).getColor(stack); }
        float f = (float)(j >> 16 & 255) / 255.0F;
        float g = (float)(j >> 8 & 255) / 255.0F;
        float h = (float)(j & 255) / 255.0F;
        //matrixStack.push();
        VertexConsumer vertexConsumer1 = ItemRenderer.getItemGlintConsumer(vertexProvider, model.getLayer(this.getTextureOverlay()), false, false);
        model.render(matrixStack, vertexConsumer1, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        VertexConsumer vertexConsumer2 = ItemRenderer.getItemGlintConsumer(vertexProvider, model.getLayer(this.getTexture()), false, false);
        model.render(matrixStack, vertexConsumer2, light, OverlayTexture.DEFAULT_UV, f, g, h, 1);
        //matrixStack.pop();
        getModel().setVisible(false);
    }
}
