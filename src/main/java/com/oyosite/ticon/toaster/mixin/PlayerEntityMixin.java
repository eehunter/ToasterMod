package com.oyosite.ticon.toaster.mixin;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.item.EffectLimb;
import com.oyosite.ticon.toaster.item.MiningArm;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Inject(method = "tick", at = @At("TAIL"))
    private void applyPermanentEffects(CallbackInfo info) {
        if (!this.world.isClient && this.age % 20 == 0) {
            TrinketComponent comp = TrinketsApi.getTrinketComponent((PlayerEntity) (Object) this);
            List<String> names = TrinketSlots.getAllSlotNames();
            names.forEach(name->{
                Item item = comp.getInventory().getStack(names.indexOf(name)).getItem();
                if (item instanceof EffectLimb) {
                    this.addStatusEffect(((EffectLimb)item).getEffectToApply());
                }
            });
        }
    }
    @Shadow @Final
    public PlayerInventory inventory;
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "isUsingEffectiveTool", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEffectiveOn(Lnet/minecraft/block/BlockState;)Z"))
    private void setItemStackHolder(BlockState block, CallbackInfoReturnable<Boolean> info) {
        this.inventory.getMainHandStack().setHolder(this);
    }
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void increaseMiningSpeed(BlockState block, CallbackInfoReturnable<Float> info) {
        TrinketComponent comp = TrinketsApi.getTrinketComponent((PlayerEntity) (Object) this);
        List<String> names = TrinketSlots.getAllSlotNames();
        Item item1 = comp.getInventory().getStack(names.indexOf(SlotGroups.CHEST+":"+ ToasterMod.LEFT_ARM)).getItem();
        Item item2 = comp.getInventory().getStack(names.indexOf(SlotGroups.CHEST+":"+ToasterMod.RIGHT_ARM)).getItem();
        MiningArm item3 = null;
        if (item1 instanceof MiningArm) {
            item3 = (MiningArm) item1;
        }
        if (item2 instanceof MiningArm) {
            if (item3 == null) item3 = ((MiningArm)item2);
            else if (((MiningArm)item2).getLevel()>item3.getLevel()) item3 = ((MiningArm)item2);
        }
        if (item3 != null) info.setReturnValue(info.getReturnValueF() + item3.getLevel() * 2);
    }
}
