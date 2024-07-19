package me.tuanzi.utils;

import me.tuanzi.events.player.PlayerTickEvent;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import static me.tuanzi.utils.Utils.printDebug;

public class DevelpomentUtils implements PlayerTickEvent {

    @Override
    public void onPlayerTick(PlayerEntity player) {
        if(FabricLoader.getInstance().isDevelopmentEnvironment() && !player.getWorld().isClient){
            if(player.isSneaking() && player.getWorld().getTime() %40 == 0){
                ItemStack itemStack = player.getMainHandStack();
                ComponentMap map = itemStack.getComponents();
                map.stream().iterator().forEachRemaining(component -> {
                    printDebug("Component: " + component);
                });
                ComponentChanges componentChanges = itemStack.getComponentChanges();
                componentChanges.entrySet().stream().iterator().forEachRemaining(componentTypeOptionalEntry -> {
                    printDebug("ComponentType: " + componentTypeOptionalEntry.getKey());
                });

                NbtCompound compound = new NbtCompound();
                compound.putInt("int",1);
                NbtComponent nbtComponent = NbtComponent.DEFAULT;
//                nbtComponent.apply((Consumer<NbtCompound>) compound);
                NbtComponent component = new Component<>(DataComponentTypes.CUSTOM_DATA,nbtComponent).value();
//                itemStack.apply(DataComponentTypes.CUSTOM_DATA,component, )


            }
        }
    }
}
