package com.oyosite.ticon.toaster.item;

import com.oyosite.ticon.toaster.ToasterMod;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.DyeableItem;

public class Colorizer extends TrinketItem implements DyeableItem {
    public Colorizer(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.HEAD) && slot.equals(ToasterMod.COLORIZER);
    }
}
