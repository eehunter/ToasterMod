package com.oyosite.ticon.toaster.misc;

import static net.minecraft.server.command.CommandManager.*;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.oyosite.ticon.toaster.component.EntityEntrypoint;
import com.oyosite.ticon.toaster.component.ProtoComponent;
import com.oyosite.ticon.toaster.entity.NaniteSlimeEntity;
import com.oyosite.ticon.toaster.mixin.ToolManagerImplAccessor;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

@SuppressWarnings("unchecked")
public class MiscRegistry {

    @SuppressWarnings("UnstableApiUsage")
    public static final Event<ToolManagerImpl.ToolHandler> FAKE_TOOLS_HANDLER = EventFactory.createArrayBacked(ToolManagerImpl.ToolHandler.class, ToolManagerImplAccessor::callToolHandlerInvoker);

    public static void registerAll() {
        FAKE_TOOLS_HANDLER.register(new MiningArmToolHandler());
        registerCommand();
        FabricDefaultAttributeRegistry.register(NaniteSlimeEntity.NANITE_SLIME_TYPE, NaniteSlimeEntity.createNaniteSlimeAttributes());
    }

    public static void registerCommand(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("protogen").then(argument("target", EntityArgumentType.players()).then(literal("setColor").then(argument("color", IntegerArgumentType.integer(0, 16777215)).executes(context -> {
                for (PlayerEntity p : EntityArgumentType.getPlayers(context, "target")) {
                    ProtoComponent proto = EntityEntrypoint.PROTO_COMP.get(p);
                    System.out.println(proto.getColor());
                    proto.setColor(IntegerArgumentType.getInteger(context, "color"), p);
                }
                return 1;
            }))).then(literal("getColor").executes(context->{
                for (PlayerEntity p : EntityArgumentType.getPlayers(context, "target")) {
                    ProtoComponent proto = EntityEntrypoint.PROTO_COMP.get(p);
                    context.getSource().getPlayer().sendMessage(new LiteralText(p.getEntityName()+"'s color is "+proto.getColor()), false);
                }
                return 1;
            }))));
        });
    }
    // Color: 16537606
    // Another color: 50687
 /*executes(context -> {
                for (PlayerEntity p : EntityArgumentType.getPlayers(context, "target")) {
                    ProtoComponent proto = EntityEntrypoint.PROTO_COMP.get(p);
                }
            })*/
}
