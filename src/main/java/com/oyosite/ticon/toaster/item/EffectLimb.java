package com.oyosite.ticon.toaster.item;

import net.minecraft.entity.effect.StatusEffectInstance;

public interface EffectLimb extends ProtoLimbItem {
    StatusEffectInstance getEffectToApply();
}
