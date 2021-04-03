package com.oyosite.ticon.toaster.mixin;

import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = ToolManagerImpl.class, remap = false)
public interface ToolManagerImplAccessor {
    @Invoker
    static ToolManagerImpl.ToolHandler callToolHandlerInvoker(ToolManagerImpl.ToolHandler[] toolHandlers) {
        throw new IllegalStateException("Accessor application error.");
    }
}
