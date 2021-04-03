package com.oyosite.ticon.toaster.item;

import com.google.common.collect.ImmutableList;
import com.oyosite.ticon.toaster.client.model.ProtoArmModel;
import com.oyosite.ticon.toaster.client.model.ProtoLegModel;
import com.oyosite.ticon.toaster.client.model.ProtoTailModel;
import com.oyosite.ticon.toaster.client.model.ProtoTailModelWide;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collections;

public class ItemInitializer {

    public static final Item STRENGTH_ARM = new EffectArm(new FabricItemSettings().group(ItemGroup.MISC))
            .setEffect(StatusEffects.STRENGTH);
    public static final Item SPEED_LEG = new EffectLeg(new FabricItemSettings().group(ItemGroup.MISC))
            .setEffect(StatusEffects.SPEED);
    public static final Item FIRE_RES_LEG = new EffectLeg(new FabricItemSettings().group(ItemGroup.MISC))
            .setEffect(StatusEffects.FIRE_RESISTANCE);
    public static final Item LEOPARD_GECKO_TAIL = new LeopardGeckoTail(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item MINING_ARM_STONE = new MiningArm(new FabricItemSettings().group(ItemGroup.MISC), 1);
    public static final Item MINING_ARM_IRON = new MiningArm(new FabricItemSettings().group(ItemGroup.MISC), 2);
    public static final Item MINING_ARM_DIAMOND = new MiningArm(new FabricItemSettings().group(ItemGroup.MISC), 3);
    public static final Item MINING_ARM_NETHERITE = new MiningArm(new FabricItemSettings().group(ItemGroup.MISC), 4);
    public static final Item BASIC_ARM = new BasicArm(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item COLORIZER = new Colorizer(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item BASIC_TAIL = new BasicTail(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item BASIC_LEG = new BasicLeg(new FabricItemSettings().group(ItemGroup.MISC));
    public static ImmutableList<Item> COLORABLE = ImmutableList.of(STRENGTH_ARM, SPEED_LEG, FIRE_RES_LEG, LEOPARD_GECKO_TAIL, MINING_ARM_STONE, MINING_ARM_IRON, MINING_ARM_DIAMOND, MINING_ARM_NETHERITE, BASIC_ARM, COLORIZER, BASIC_TAIL, BASIC_LEG);
    //public static final Item MINING_ARM_IRON_DUMMY = new ArmDummy(new FabricItemSettings());
    public static void initializeItems() {
        Registry.register(Registry.ITEM, new Identifier("toastermod", "strength_arm"), STRENGTH_ARM);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "mining_arm_stone"), MINING_ARM_STONE);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "mining_arm_iron"), MINING_ARM_IRON);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "mining_arm_diamond"), MINING_ARM_DIAMOND);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "mining_arm_netherite"), MINING_ARM_NETHERITE);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "speed_leg"), SPEED_LEG);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "fire_res_leg"), FIRE_RES_LEG);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "leopard_gecko_tail"), LEOPARD_GECKO_TAIL);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "basic_arm"), BASIC_ARM);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "colorizer"), COLORIZER);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "basic_tail"), BASIC_TAIL);
        Registry.register(Registry.ITEM, new Identifier("toastermod", "basic_leg"), BASIC_LEG);
        //Registry.register(Registry.ITEM, new Identifier("toastermod", "mining_arm_iron_dummy"), MINING_ARM_IRON_DUMMY);
    }
    @Environment(EnvType.CLIENT)
    public static void initLimbModels(){
        ((EffectArm)STRENGTH_ARM).setModel(new ProtoArmModel())
                .setTexture(new Identifier("toastermod", "textures/entity/effect_arm_base.png"))
                .setOverlay(new Identifier("toastermod", "textures/entity/strength_arm_overlay.png"));
        ((EffectLeg)SPEED_LEG).setModel(new ProtoLegModel())
                .setTexture(new Identifier("toastermod", "textures/entity/effect_leg_base.png"))
                .setOverlay(new Identifier("toastermod", "textures/entity/speed_leg_overlay.png"));
        ((EffectLeg)FIRE_RES_LEG).setModel(new ProtoLegModel())
                .setTexture(new Identifier("toastermod", "textures/entity/effect_leg_base.png"))
                .setOverlay(new Identifier("toastermod", "textures/entity/fire_res_leg_overlay.png"));
        ((LeopardGeckoTail)LEOPARD_GECKO_TAIL).setModel(new ProtoTailModelWide());
        ((MiningArm)MINING_ARM_STONE).setModel(new ProtoArmModel())
                .setOverlay(new Identifier("toastermod", "textures/entity/iron_mining_arm_overlay.png"));
        ((MiningArm)MINING_ARM_IRON).setModel(new ProtoArmModel())
                .setOverlay(new Identifier("toastermod", "textures/entity/iron_mining_arm_overlay.png"));
        ((MiningArm)MINING_ARM_DIAMOND).setModel(new ProtoArmModel())
                .setOverlay(new Identifier("toastermod", "textures/entity/diamond_mining_arm_overlay.png"));
        ((MiningArm)MINING_ARM_NETHERITE).setModel(new ProtoArmModel())
                .setOverlay(new Identifier("toastermod", "textures/entity/netherite_mining_arm_overlay.png"));
        ((BasicArm)BASIC_ARM).setModel(new ProtoArmModel())
                .setOverlay(new Identifier("toastermod", "textures/entity/arm_overlay.png"));
        ((BasicTail)BASIC_TAIL).setModel(new ProtoTailModel());
        ((BasicLeg)BASIC_LEG).setModel(new ProtoLegModel())
                .setTexture(new Identifier("toastermod", "textures/entity/effect_leg_base.png"))
                .setOverlay(new Identifier("toastermod", "textures/entity/leg_overlay.png"));

        for(Item item : COLORABLE){
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).hasColor(stack) && tintIndex==0 ? ((DyeableItem) stack.getItem()).getColor(stack) : 0xFFFFFF, item);
        }
    }


}
