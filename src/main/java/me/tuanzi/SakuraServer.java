package me.tuanzi;

import me.tuanzi.bedrock.GeyserExtension;
import me.tuanzi.blocks.BlockRegisty;
import me.tuanzi.effects.EffectRegistry;
import me.tuanzi.enchantments.EnchantmentRegistry;
import me.tuanzi.items.ItemsRegisty;
import me.tuanzi.items.ToolRegistry;
import me.tuanzi.recipe.TimespaceUpdateRecipe;
import me.tuanzi.utils.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.geysermc.geyser.api.GeyserApi;

import static me.tuanzi.items.ItemsRegisty.TEST_ITEM;
import static me.tuanzi.network.C2SChannel.registerC2SPackets;
import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class SakuraServer implements ModInitializer {
    public static boolean hasGeyser = FabricLoader.getInstance().isModLoaded("geyser-fabric");
    public static boolean hasFloodgate = FabricLoader.getInstance().isModLoaded("floodgate");
    //specialRecipe
    public static RecipeSerializer<TimespaceUpdateRecipe> TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER;


    @Override
    public void onInitialize() {
        //reg
        //test
        Registry.register(Registries.ITEM, new Identifier(MODID, "test_item"), TEST_ITEM);
        //block
        new BlockRegisty();
        //item
        new ItemsRegisty();
        //tool
        new ToolRegistry();
         //enchantment
        new EnchantmentRegistry();
         //itemGroup
        new ItemGroupRegistry();
        //effect
        new EffectRegistry();
       //recipe
        TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER = RecipeSerializer.register("timespace_update",new SpecialRecipeSerializer<>(TimespaceUpdateRecipe::new));
/*        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(MODID,"timespace_update"),TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER);*/
        //statistics
        new StatsRegisty();
        //sound
        new SoundRegistry();
        //event
        new EventRegister();
        //command
        new CommandRegister();
        //network
        registerC2SPackets();
        //geyser
        if (hasGeyser) {
            ServerLifecycleEvents.SERVER_STARTING.register((server -> {
                GeyserApi.api().eventBus().register(new GeyserExtension(), this);
            }));
        }
        //placeOre
        new PlacementRegistry();
        printDebugLog("加载成功!");
        printDebugLog("是否加载Geyser:" + hasGeyser);
        printDebugLog("是否加载floodgate:" + hasFloodgate);
    }


}