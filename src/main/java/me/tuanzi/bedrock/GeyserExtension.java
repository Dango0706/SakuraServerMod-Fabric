package me.tuanzi.bedrock;

import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.block.custom.CustomBlockData;
import org.geysermc.geyser.api.block.custom.component.BoxComponent;
import org.geysermc.geyser.api.block.custom.component.CustomBlockComponents;
import org.geysermc.geyser.api.block.custom.component.GeometryComponent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomBlocksEvent;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.api.item.custom.CustomItemOptions;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemData;

import static me.tuanzi.SakuraServer.printLog;

public class GeyserExtension implements Extension {

    @Subscribe
    public void onDefineCustomBlocks(GeyserDefineCustomBlocksEvent event) {
        //...

        CustomBlockComponents customBlockComponents =  CustomBlockComponents.builder()
                .collisionBox(BoxComponent.fullBox())
                .selectionBox(BoxComponent.fullBox())
                .geometry(GeometryComponent.builder().identifier("sakura_server:lift1").build())
                .lightDampening(0)
                .lightEmission(0)
                .friction(1f)
                .build();

        CustomBlockData redstoneDot = CustomBlockData.builder()
                .name("sakura_server:lift1")
                .components(customBlockComponents)
                .build();

        event.register(redstoneDot);
        event.registerItemOverride("sakura_server:lift1", redstoneDot);

    }
    @Subscribe
    public void onGeyserDefineCustomItemsEvent(GeyserDefineCustomItemsEvent event) {
        NonVanillaCustomItemData emerald_apple = NonVanillaCustomItemData.builder()
                .name("emerald_apple_1")
                .identifier("sakura_server:emerald_apple_1")
                .javaId(15241000)
                .canAlwaysEat(true)
                .customItemOptions(CustomItemOptions.builder().build())
                .textureSize(32)
                .build();
        event.register(emerald_apple);
        printLog("成?:" + event.register(emerald_apple));
    }

}
