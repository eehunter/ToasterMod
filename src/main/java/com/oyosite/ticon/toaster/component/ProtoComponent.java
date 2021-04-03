package com.oyosite.ticon.toaster.component;

import com.oyosite.ticon.toaster.ToasterMod;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

public interface ProtoComponent extends AutoSyncedComponent {

    public int getColor();
    public void setColor(int color, PlayerEntity p);

    public class Impl implements ProtoComponent{

        private int color = ToasterMod.RANDOM.nextInt(16777216);

        @Override
        public void readFromNbt(CompoundTag tag) {
            color = tag.getInt("color");
        }

        @Override
        public void writeToNbt(CompoundTag tag) {
            tag.putInt("color", color);
        }

        @Override
        public int getColor() {
            return color;
        }

        @Override
        public void setColor(int color, PlayerEntity p) {
            this.color = color;
            EntityEntrypoint.PROTO_COMP.sync(p);
            for (ServerPlayerEntity player : PlayerLookup.tracking(p)) {
                EntityEntrypoint.PROTO_COMP.sync(player);
            }
        }
    }
}
