package com.oyosite.ticon.toaster.mixin;

import com.oyosite.ticon.toaster.misc.MiscRegistry;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = ToolManagerImpl.class, remap = false)
public abstract class ToolManagerImplMixin {
    /*@Inject(method = "handleIsEffectiveOnIgnoresVanilla", at = @At(value = "TAIL"), cancellable = true)
    private static void invokeFakeToolHandler1(BlockState state, ItemStack stack, LivingEntity user, boolean vanillaResult, CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValueZ()) info.setReturnValue(MiscRegistry.FAKE_TOOLS_HANDLER.invoker().isEffectiveOn(null, state, stack, user).isAccepted());
    }*/
    @Inject(method = "handleIsEffectiveOnIgnoresVanilla", at = @At(("HEAD")), cancellable = true)
    private static void invokeFakeToolHandler(BlockState state, ItemStack stack, LivingEntity user, boolean vanillaResult, CallbackInfoReturnable<Boolean> info) {
        Entity e = ((ItemStackAccessor) (Object) stack).getHolder();
        if (MiscRegistry.FAKE_TOOLS_HANDLER.invoker().isEffectiveOn(null, state, stack, (user == null && e instanceof LivingEntity) ? (LivingEntity) e : null).isAccepted()) info.setReturnValue(true);
    }
    /*@ModifyVariable(method = "handleIsEffectiveOnIgnoresVanilla", argsOnly = true, at = @At("HEAD"))
    private static LivingEntity getUserFromStackHolder(LivingEntity user, BlockState state, ItemStack stack) {
        Entity e = ((ItemStackAccessor) (Object) stack).getHolder();
        return (user == null && e instanceof LivingEntity) ? (LivingEntity) e : null;
    }*/
}
