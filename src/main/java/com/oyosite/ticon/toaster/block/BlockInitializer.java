package com.oyosite.ticon.toaster.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class BlockInitializer {
    public static final ArrayList<ToasterBlock> AUTO_REGISTRY = new ArrayList<>();
    public static final Material PLATING;
    static {
        PLATING = (new Material.Builder(MaterialColor.IRON)).build();
    }

    public static final ToasterBlock BATTERED_PLATING = new ToasterBlock(FabricBlockSettings.of(PLATING).strength(4.0f), new Identifier("toastermod", "battered_plating")).autoRegister();
    public static final ToasterBlock RUSTED_PLATING = new ToasterBlock(FabricBlockSettings.of(PLATING).strength(4.0f), new Identifier("toastermod", "rusted_plating")).autoRegister();
    public static final ToasterBlock PLATING_PATH = new ToasterBlock(FabricBlockSettings.of(PLATING).strength(4.0f), new Identifier("toastermod", "plating_path")).autoRegister();
    public static final GrateBlock PLATING_GRATE = (GrateBlock) new GrateBlock(FabricBlockSettings.of(PLATING).strength(4.0f), new Identifier("toastermod", "plating_grate")).autoRegister();
    public static final ToasterBlock PLATING_LIGHT = new ToasterBlock(FabricBlockSettings.of(PLATING).strength(4.0f).luminance(15).nonOpaque(), new Identifier("toastermod", "plating_light")).autoRegister();


    public static void initializeBlocks() {
        AUTO_REGISTRY.forEach(ToasterBlock::register);
    }
}
