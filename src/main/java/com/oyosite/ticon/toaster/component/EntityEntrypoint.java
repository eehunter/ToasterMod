package com.oyosite.ticon.toaster.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class EntityEntrypoint implements EntityComponentInitializer {
    public static final ComponentKey<ProtoComponent> PROTO_COMP = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("toastermod:protocomp"), ProtoComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PROTO_COMP, player -> new ProtoComponent.Impl(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
