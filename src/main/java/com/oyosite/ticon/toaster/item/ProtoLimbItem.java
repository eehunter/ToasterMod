package com.oyosite.ticon.toaster.item;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.component.EntityEntrypoint;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ProtoLimbItem extends DyeableItem {
    @Environment(EnvType.CLIENT)
    BipedEntityModel<LivingEntity> getModel();
    //default int getColor(ItemStack stack) {return -1;}
    static int getColor(PlayerEntity player, String slot){
        int j = EntityEntrypoint.PROTO_COMP.get(player).getColor();
        ItemStack stack = TrinketsApi.getTrinketComponent(player).getInventory().getStack(TrinketSlots.getAllSlotNames().indexOf(slot));
        ItemStack stack1 = TrinketsApi.getTrinketComponent(player).getInventory().getStack(TrinketSlots.getAllSlotNames().indexOf(SlotGroups.HEAD+":"+ ToasterMod.COLORIZER));
        if (stack1.getItem() instanceof DyeableItem && ((DyeableItem)stack1.getItem()).hasColor(stack1)) j = ((DyeableItem)stack1.getItem()).getColor(stack1);
        if (stack.getItem() instanceof ProtoLimbItem){ if (((ProtoLimbItem) stack.getItem()).hasColor(stack)) j = ((ProtoLimbItem) stack.getItem()).getColor(stack); }
        return j;
    }
}
