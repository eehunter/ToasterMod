package com.oyosite.ticon.toaster.block.blockentity;

import com.oyosite.ticon.toaster.ToasterMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public class GrateBlockEntity extends BlockEntity implements Tickable {
    public GrateBlockEntity() {
        super(ToasterMod.GRATE_BLOCK_ENTITY);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        return tag;
    }

    @Override
    public void tick() {

    }
}
