package com.oyosite.ticon.toaster.block;

import com.oyosite.ticon.toaster.block.blockentity.GrateBlockEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class GrateBlock extends ToasterBlock implements BlockEntityProvider {
    public GrateBlock(Settings settings, Identifier ID) {
        super(settings, ID);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new GrateBlockEntity();
    }
}
