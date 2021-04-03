package com.oyosite.ticon.toaster;

import com.oyosite.ticon.toaster.client.NaniteSlimeRenderer;
import com.oyosite.ticon.toaster.entity.NaniteSlimeEntity;
import com.oyosite.ticon.toaster.item.ItemInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class ToasterModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemInitializer.initLimbModels();
        EntityRendererRegistry.INSTANCE.register(NaniteSlimeEntity.NANITE_SLIME_TYPE, (dispatcher, context) -> new NaniteSlimeRenderer(dispatcher));
    }
}
