package com.oyosite.ticon.toaster.item;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.client.model.ProtoArmModel;
import com.oyosite.ticon.toaster.component.EntityEntrypoint;
import com.oyosite.ticon.toaster.misc.MiningArmToolHandler;
import dev.emi.trinkets.api.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.List;

public class MiningArm extends TrinketItem implements ProtoLimbItem {

    private final int level;
    private final List<Item> levelTools;
    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    private Identifier texture = new Identifier("toastermod", "textures/entity/mining_arm_base.png");
    private Identifier overlay;

    /*
    * [material][something][material]
    * [wool][previous tier][wool]
    * [material][material pickaxe][material]
    */

    public MiningArm(Settings settings, int level) {
        super(settings.maxCount(1));
        this.level = level;
        if(MiningArmToolHandler.levels.get(level-1)!=null)levelTools = MiningArmToolHandler.levels.get(level-1);
        else levelTools = MiningArmToolHandler.netheriteLevel;
        /*switch (level){
            case 1:
                levelTools = MiningArmToolHandler.stoneLevel;
            case 2:
                levelTools = MiningArmToolHandler.ironLevel;
            case 3:
                levelTools = MiningArmToolHandler.diamondLevel;
            default:
                levelTools = MiningArmToolHandler.netheriteLevel;
        }*/
    }

    public MiningArm(Settings settings, int level, List<Item> levelTools) {
        super(settings);
        this.level = level;
        this.levelTools = levelTools;
    }

    public int getLevel() {
        return this.level;
    }

    public List<Item> getLevelTools(){
        return this.levelTools;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.CHEST)&&(slot.equals(ToasterMod.LEFT_ARM)||slot.equals(ToasterMod.RIGHT_ARM));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        if (player.isInvisible()) return;
        getModel().leftArm.copyPositionAndRotation(model.leftArm);
        getModel().rightArm.copyPositionAndRotation(model.rightArm);
        int j = ProtoLimbItem.getColor(player, slot);
        float f = (float)(j >> 16 & 255) / 255.0F;
        float g = (float)(j >> 8 & 255) / 255.0F;
        float h = (float)(j & 255) / 255.0F;
        VertexConsumer vertexBuilder1 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getTexture()), false, false);
        this.getModel().renderHand(slot.endsWith("rightarm"), matrixStack, vertexBuilder1, light, OverlayTexture.DEFAULT_UV, f, g, h, 1);
        VertexConsumer vertexBuilder2 = ItemRenderer.getItemGlintConsumer(vertexConsumer, model.getLayer(this.getOverlay()), false, false);
        this.getModel().renderHand(slot.endsWith("rightarm"), matrixStack, vertexBuilder2, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    public MiningArm setModel(BipedEntityModel<LivingEntity> model){
        this.model = model;
        return this;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ProtoArmModel getModel() {
        return (ProtoArmModel) model;
    }

    @Environment(EnvType.CLIENT)
    public MiningArm setTexture(Identifier texture){
        this.texture = texture;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getTexture(){ return this.texture; }

    @Environment(EnvType.CLIENT)
    public MiningArm setOverlay(Identifier overlay){
        this.overlay = overlay;
        return this;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getOverlay(){
        return this.overlay;
    }



    /*@Override
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        matrixStack.push();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ItemInitializer.MINING_ARM_IRON_DUMMY);
        if (slot.endsWith("leftarm")) {
            Trinket.translateToLeftArm(matrixStack, model, player, headYaw, headPitch);
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
            matrixStack.translate(0.0D, 0.125D, 0.03125D);
            itemRenderer.renderItem(null, stack, ModelTransformation.Mode.FIXED, true, matrixStack, vertexConsumer, null, light, 1);
        } else {
            Trinket.translateToRightArm(matrixStack, model, player, headYaw, headPitch);
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
            matrixStack.translate(0.0D, 0.125D, 0.03125D);
            itemRenderer.renderItem(null, stack, ModelTransformation.Mode.FIXED, false, matrixStack, vertexConsumer, null, light, 1);
        }
        //GlStateManager.scalef(0.2F, -0.2F, 0.2F);
        matrixStack.pop();
    }*/
}
