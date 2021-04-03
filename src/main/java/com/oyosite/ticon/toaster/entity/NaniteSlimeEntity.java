package com.oyosite.ticon.toaster.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@SuppressWarnings("unchecked")
public class NaniteSlimeEntity extends SlimeEntity {
    public static final EntityType NANITE_SLIME_TYPE;
    public NaniteSlimeEntity(EntityType entityType, World world) {
        super(entityType, world);
    }
    public NaniteSlimeEntity(World world){
        this(NANITE_SLIME_TYPE, world);
    }
    static {
        NANITE_SLIME_TYPE = register("nanite_slime", EntityType.Builder.create(NaniteSlimeEntity::new, SpawnGroup.MONSTER).setDimensions(2.04F, 2.04F).maxTrackingRange(10));
    }
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier("toastermod", id), type.build(id));
    }
    public static DefaultAttributeContainer.Builder createNaniteSlimeAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224D);
    }

}
