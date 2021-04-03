package com.oyosite.ticon.toaster;

//import com.oyosite.ticon.toaster.command.ProtogenCommand;

import com.oyosite.ticon.toaster.block.BlockInitializer;
import com.oyosite.ticon.toaster.block.GrateBlock;
import com.oyosite.ticon.toaster.block.blockentity.GrateBlockEntity;
import com.oyosite.ticon.toaster.item.ItemInitializer;
import com.oyosite.ticon.toaster.misc.MiscRegistry;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketSlots;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Random;

public class ToasterMod implements ModInitializer {

    public static BlockEntityType<GrateBlockEntity> GRATE_BLOCK_ENTITY;

    public static final String LEFT_ARM = "leftarm";
    public static final String RIGHT_ARM = "rightarm";
    public static final String LEFT_LEG = "leftleg";
    public static final String RIGHT_LEG = "rightleg";
    public static final String TAIL = "tail";
    public static final String COLORIZER = "colorizer";

    public static Random RANDOM = new Random();

    @Override
    public void onInitialize() {
        GRATE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "toastermod:grate", BlockEntityType.Builder.create(GrateBlockEntity::new, BlockInitializer.PLATING_GRATE).build(null));
        TrinketSlots.addSlot(SlotGroups.CHEST, LEFT_ARM, new Identifier("trinkets", "textures/gui/blank_back.png"));//leftarm.png
        TrinketSlots.addSlot(SlotGroups.CHEST, RIGHT_ARM, new Identifier("trinkets", "textures/gui/blank_back.png"));//rightarm.png
        TrinketSlots.addSlot(SlotGroups.LEGS, LEFT_LEG, new Identifier("trinkets", "textures/gui/blank_back.png"));//leftleg.png
        TrinketSlots.addSlot(SlotGroups.LEGS, RIGHT_LEG, new Identifier("trinkets", "textures/gui/blank_back.png"));//rightleg.png
        TrinketSlots.addSlot(SlotGroups.LEGS, TAIL, new Identifier("trinkets", "textures/gui/blank_back.png"));//tail.png
        TrinketSlots.addSlot(SlotGroups.HEAD, COLORIZER, new Identifier("trinkets", "textures/gui/blank_back.png"));
        ItemInitializer.initializeItems();
        BlockInitializer.initializeBlocks();
        MiscRegistry.registerAll();

    }
    //Original color in server: 16237110
}
