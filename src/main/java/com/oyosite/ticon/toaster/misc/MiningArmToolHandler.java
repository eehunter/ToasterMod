package com.oyosite.ticon.toaster.misc;

import com.oyosite.ticon.toaster.ToasterMod;
import com.oyosite.ticon.toaster.item.MiningArm;
import com.oyosite.ticon.toaster.mixin.ItemStackAccessor;
import com.oyosite.ticon.toaster.mixin.TMIEntryImplAccessor;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class MiningArmToolHandler implements ToolManagerImpl.ToolHandler {
    public static final List<Item> stoneLevel = Arrays.asList(Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD, Items.SHEARS);
    public static final List<Item> ironLevel = Arrays.asList(Items.IRON_AXE, Items.IRON_HOE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.SHEARS);
    public static final List<Item> diamondLevel = Arrays.asList(Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD, Items.SHEARS);
    public static final List<Item> netheriteLevel = Arrays.asList(Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD, Items.SHEARS);
    public static final List<List<Item>> levels = Arrays.asList(stoneLevel, ironLevel, diamondLevel, netheriteLevel);
    @Override
    public @NotNull ActionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {//System.out.println("Is this working?");//System.out.println(user);//Entity u = ((ItemStackAccessor)(Object)stack).getHolder();
        if(!(user instanceof PlayerEntity)) return ActionResult.PASS;
        TrinketComponent comp = TrinketsApi.getTrinketComponent((PlayerEntity) user);
        List<String> names = TrinketSlots.getAllSlotNames();
        Item item1 = comp.getInventory().getStack(names.indexOf(SlotGroups.CHEST+":"+ToasterMod.LEFT_ARM)).getItem();
        Item item2 = comp.getInventory().getStack(names.indexOf(SlotGroups.CHEST+":"+ToasterMod.RIGHT_ARM)).getItem();//System.out.println(item1.toString());//System.out.println(item2.toString());
        if (!(item1 instanceof MiningArm || item2 instanceof MiningArm)) return ActionResult.PASS;
        MiningArm armItem;
        if (item1 instanceof MiningArm) {
            armItem = (MiningArm)item1;
            ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBlock());
            if (entry != null) {
                int requiredMiningLevel = Arrays.stream(((TMIEntryImplAccessor) entry).getTagLevels()).min().orElse(-1);
                return requiredMiningLevel >= 0 && requiredMiningLevel <= armItem.getLevel() ? ActionResult.SUCCESS : ActionResult.PASS;
            }
            for (Item tool : armItem.getLevelTools()) if (new ItemStack(tool).isEffectiveOn(state)) return ActionResult.SUCCESS;
        }
        if(item2 instanceof MiningArm) {
            armItem = (MiningArm)item2;
            ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBlock());
            if (entry != null) {
                int requiredMiningLevel = Arrays.stream(((TMIEntryImplAccessor) entry).getTagLevels()).min().orElse(-1);
                return requiredMiningLevel >= 0 && requiredMiningLevel <= armItem.getLevel() ? ActionResult.SUCCESS : ActionResult.PASS;
            }
            for (Item tool : armItem.getLevelTools()) if (new ItemStack(tool).isEffectiveOn(state)) return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    public MiningArmToolHandler(){
        System.out.println("MiningArmToolHandler created.");
    }
}
