package me.tuanzi.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;

//TODO
public class Config {


    public static final ConfigClassHandler<Config> INSTANCE = ConfigClassHandler.createBuilder(Config.class)
            .id(new Identifier(MODID, "client_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("sakura_server_config.json")).build())   // Change "modid" to your mod ID.
            .build();

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> builder
                        .title(Text.translatable("config.modid.title"))
                        .category(ConfigCategory.createBuilder()
                                .name(Text.translatable("config.modid.title"))
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.translatable("config.modid.option.treasureDropPossibility"))
                                        .description(OptionDescription.of(Text.translatable("config.modid.option.treasureDropPossibility.desc")))
                                        .binding(
                                                16,
                                                () -> config.veinMineCountPerLevel,
                                                value -> config.veinMineCountPerLevel = value
                                        )
                                        .controller(opt -> IntegerFieldControllerBuilder.create(opt).range(0, 64)
                                                        .formatValue(value -> Text.literal(value * 64 + "%")))
                                        .build())
                                .build()))
                .generateScreen(parent);
    }


    @SerialEntry
    public int veinMineCountPerLevel = 16;


}
