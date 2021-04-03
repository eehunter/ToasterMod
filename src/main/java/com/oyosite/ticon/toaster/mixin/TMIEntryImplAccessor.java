package com.oyosite.ticon.toaster.mixin;

import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl$EntryImpl", remap = false)
public interface TMIEntryImplAccessor {
    @Accessor
    int[] getTagLevels();
}
