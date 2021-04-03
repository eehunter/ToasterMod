package com.oyosite.ticon.toaster.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ToasterBlock extends Block {
    private final Identifier ID;
    private final FabricItemSettings itemSettings;
    private static FabricItemSettings defaultSettings = new FabricItemSettings().group(ItemGroup.MISC);
    public ToasterBlock(Settings settings, Identifier ID) { this(settings, defaultSettings, ID); }
    public ToasterBlock(Settings settings, FabricItemSettings itemSettings, Identifier ID) {
        super(settings);
        this.ID = ID;
        this.itemSettings = itemSettings;
    }
    public void register(){
        Registry.register(Registry.BLOCK, this.ID, this);
        Registry.register(Registry.ITEM, this.ID, new BlockItem(this, this.itemSettings));
    }
    public ToasterBlock autoRegister(){
        BlockInitializer.AUTO_REGISTRY.add(this);
        return this;
    }
}
