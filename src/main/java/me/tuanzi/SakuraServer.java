package me.tuanzi;

import me.tuanzi.bedrock.GeyserExtension;
import me.tuanzi.blocks.angel_block.AngelBlock;
import me.tuanzi.blocks.angel_block.AngelBlockEntity;
import me.tuanzi.blocks.lift.Lift;
import me.tuanzi.blocks.sorter.Sorter;
import me.tuanzi.blocks.sorter.SorterBlockEntity;
import me.tuanzi.effects.AngelWings;
import me.tuanzi.effects.DragonSwordEffect;
import me.tuanzi.effects.Healbane;
import me.tuanzi.effects.LiftCDEffect;
import me.tuanzi.enchantments.all.SoulBound;
import me.tuanzi.enchantments.bow.AdvancedPower;
import me.tuanzi.enchantments.sword.AdvancedDamageEnchantment;
import me.tuanzi.enchantments.sword.Education;
import me.tuanzi.enchantments.tools.VeinMine;
import me.tuanzi.items.TestItem;
import me.tuanzi.items.display.DrawBlueSomething;
import me.tuanzi.items.display.DrawGoldenSomething;
import me.tuanzi.items.display.DrawPurpleSomething;
import me.tuanzi.items.display.DrawSomething;
import me.tuanzi.items.foods.EmeraldApple;
import me.tuanzi.items.functional.*;
import me.tuanzi.items.msic.NetheriteHammer;
import me.tuanzi.items.swords.DragonSword;
import me.tuanzi.items.swords.StardustWand;
import me.tuanzi.items.tools.AntimatterPickaxe;
import me.tuanzi.items.tools.GamblingPickaxe;
import me.tuanzi.items.utils.SakuraBlockItem;
import me.tuanzi.items.utils.SakuraItem;
import me.tuanzi.items.utils.SakuraToolMaterial;
import me.tuanzi.recipe.TimespaceUpdateRecipe;
import me.tuanzi.utils.CommandRegister;
import me.tuanzi.utils.EventRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.geysermc.geyser.api.GeyserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import static me.tuanzi.items.utils.ItemUtils.getAllModEnchantmentBooks;
import static me.tuanzi.network.C2SChannel.registerC2SPackets;

public class SakuraServer implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static final String MODID = "sakura_server";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    //block
    public static final Block LIFT = new Lift(FabricBlockSettings.create().strength(4.0f).requiresTool());
    public static final Block ANGEL_BLOCK = new AngelBlock(FabricBlockSettings.create().strength(75, 3500).requiresTool());
    public static final Block SORTER = new Sorter(FabricBlockSettings.create().strength(15, 1200).nonOpaque().requiresTool());
    public static final Block TIMESPACE_ORE = new Block(FabricBlockSettings.create().strength(50, 1200).nonOpaque().requiresTool());
    public static final Block STELLAR_PROMISE_ORE = new Block(FabricBlockSettings.create().strength(50, 1200).nonOpaque().requiresTool());
    //item
    public static final Item EMERALD_APPLE = new EmeraldApple();
    public static final Item TEST_ITEM = new TestItem(new FabricItemSettings());
    public static final Item SOUL_GEM = new SoulGem(new FabricItemSettings().maxCount(64)).setRarity(5);
    public static final Item MOVE_VILLAGER = new MoveVillager(new FabricItemSettings().maxCount(1)).setRarity(3);
    public static final Item MAGNET = new Magnet(new FabricItemSettings().maxCount(1).maxDamage(350)).setRarity(2).setDesc(Text.translatable("item.sakura_server.magnet.desc"));
    public static final Item ANGEL_WINGS_CORE = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(4);
    public static final Item SACRED_FEATHERS = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_1 = new SakuraItem(new FabricItemSettings()).setRarity(2);
    public static final Item COMPRESSED_COPPER_2 = new SakuraItem(new FabricItemSettings()).setRarity(2);
    public static final Item COMPRESSED_COPPER_3 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_4 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_5 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_6 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_7 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_8 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_9 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item ANGEL_BLOCK_FRAMEWORK = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(4);
    public static final Item DRAW_SOMETHING = new DrawSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_BLUE_SOMETHING = new DrawBlueSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_PURPLE_SOMETHING = new DrawPurpleSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_GOLDEN_SOMETHING = new DrawGoldenSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DREAM_KEY = new DreamKey(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.dream_key.desc"),Text.translatable("item.sakura_server.see_pool"))
            ;
    public static final Item TEN_DREAM_KEY = new TenDreamKey(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.ten_dream_key.desc"),Text.translatable("item.sakura_server.see_pool"));
    public static final Item STELLAR_PROMISE = new StellarPromise(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.stellar_promise.desc"),Text.translatable("item.sakura_server.see_pool"))
            ;
    public static final Item TEN_STELLAR_PROMISE = new TenStellarPromise(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.ten_stellar_promise.desc"),Text.translatable("item.sakura_server.see_pool"))

            ;
    public static final Item REPLICATORS = new SakuraItem(new FabricItemSettings().fireproof())
            .setRarity(5).setDesc(Text.translatable("item.sakura_server.replicators.desc"),Text.translatable("item.sakura_server.see_pool"));
    public static final Item VAST_STAR = new VastStar(new FabricItemSettings().fireproof()).setRarity(5);
    public static final Item TIMESPACE_SHARDS = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(3);
    public static final Item TIMESPACE_GEM = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(3);
    public static final Item DEATH_SCROLL = new DeathScroll(new FabricItemSettings()).setRarity(3);
    public static final Item NETHERITE_HAMMER= new NetheriteHammer(new FabricItemSettings().fireproof().maxCount(1).maxDamage(500)).setRarity(3);
    //toolMaterial
    public static final SakuraToolMaterial RARE_TOOLS = new SakuraToolMaterial(3);
    public static final SakuraToolMaterial EPIC_TOOLS = new SakuraToolMaterial(4);
    public static final SakuraToolMaterial LEG_TOOLS = new SakuraToolMaterial(5);
    public static final SakuraToolMaterial TIMESPACE_MATERIAL = new SakuraToolMaterial(3, 5, 2568,12.0f, Ingredient.ofItems(TIMESPACE_GEM));
    //TimeSpace
    public static final SwordItem TIMESPACE_SWORD = new SwordItem(TIMESPACE_MATERIAL, 10, 1.6f - 4.0f, new Item.Settings().fireproof());
    public static final PickaxeItem TIMESPACE_PICKAXE = new PickaxeItem(TIMESPACE_MATERIAL, 8, 1.2f- 4.0f, new Item.Settings().fireproof());
    public static final AxeItem TIMESPACE_AXE = new AxeItem(TIMESPACE_MATERIAL, 12, 1.0f- 4.0f, new Item.Settings().fireproof());
    public static final ShovelItem TIMESPACE_SHOVEL = new ShovelItem(TIMESPACE_MATERIAL, 8.5f, 1.0f- 4.0f, new Item.Settings().fireproof());
    public static final HoeItem TIMESPACE_HOE = new HoeItem(TIMESPACE_MATERIAL, 2, 4.0f - 4.0f, new Item.Settings().fireproof());
    //sword
    public static final SwordItem DRAGON_SWORD = new DragonSword();
    public static final SwordItem STARDUST_WAND = new StardustWand();
    //tool
    public static final PickaxeItem GAMBLING_PICKAXE = new GamblingPickaxe();
    public static final PickaxeItem ANTIMATTER_PICKAXE = new AntimatterPickaxe();
    //effect
    public static final StatusEffect LIFT_CD = new LiftCDEffect();
    public static final StatusEffect DRAGON_SWORD_EFFECT = new DragonSwordEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "775D699C-A100-5520-0E0D-7DEC4BDD99AB", 4.0f, EntityAttributeModifier.Operation.ADDITION)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "F7589552-C50D-3067-C438-1EFEDBD53D8B", 0.5f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "F7589552-C50D-3067-C438-1EFEDBD53D8B", 0.3f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final StatusEffect HEALBANE = new Healbane();
    public static final StatusEffect ANGEl_WINGS = new AngelWings();
    //stats
    public static final Identifier HIGHEST_DAMAGE = new Identifier(MODID, "highest_damage");
    public static final Identifier HIGHEST_HURT = new Identifier(MODID, "highest_hurt");
    public static final Identifier LIFT_ALL = new Identifier(MODID, "lift_all");
    public static final Identifier LIFT_UP = new Identifier(MODID, "lift_up");
    public static final Identifier LIFT_DOWN = new Identifier(MODID, "lift_down");
    public static final Identifier VEIN_MINE_COUNT = new Identifier(MODID, "vein_mine_count");
    public static final Identifier DRAW_COUNT = new Identifier(MODID, "draw_count");
    public static final Identifier DRAW_GOLDEN_COUNT = new Identifier(MODID, "draw_golden_count");
    public static final Identifier DRAW_PURPLE_COUNT = new Identifier(MODID, "draw_purple_count");
    public static final Identifier DRAW_BLUE_COUNT = new Identifier(MODID, "draw_blue_count");
    public static final Identifier BREAK_BLOCK_COUNT = new Identifier(MODID, "break_block_count");
    public static final Identifier TOTAL_DAMAGE_TAKEN = new Identifier(MODID, "total_damage_taken");
    public static final Identifier TOTAL_ONLINE_TIME = new Identifier(MODID, "total_online_time");
    public static final Identifier TOTAL_DAMAGE_CAUSED = new Identifier(MODID, "total_damage_caused");
    public static final Identifier TOTAL_PLAYER_DAMAGE_TAKEN = new Identifier(MODID, "total_player_damage_taken");
    public static final Identifier TOTAL_PLAYER_DAMAGE_CAUSED = new Identifier(MODID, "total_player_damage_caused");
    public static final Identifier PLACE_BLOCK_COUNT = new Identifier(MODID, "place_block_count");
    //sounds
    public static final Identifier DRAGON_ROAR = new Identifier(MODID, "dragon_roar");
    public static SoundEvent DRAGON_ROAR_SOUND = SoundEvent.of(DRAGON_ROAR);
    public static final Identifier MAGNET_USE = new Identifier(MODID, "magnet");
    public static SoundEvent MAGNET_USE_SOUND = SoundEvent.of(MAGNET_USE);
    //enchantment
    public static final Enchantment SOUL_BOUND = new SoulBound(Enchantment.Rarity.RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment VEIN_MINE = new VeinMine(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ADVANCED_SHARPNESS = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 0, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_SMITE = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 1, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_BANE_OF_ARTHROPODS = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 2, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_POWER = new AdvancedPower(Enchantment.Rarity.VERY_RARE, 4, EquipmentSlot.MAINHAND);
    public static final Enchantment EDUCATION = new Education(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, 4, EquipmentSlot.MAINHAND);
    //orePlace
    public static final RegistryKey<PlacedFeature> TIMESPACE_ORE_PLACEMENT = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier(MODID, "timespace_ore_placement"));
    public static final RegistryKey<PlacedFeature> STELLAR_PROMISE_ORE_PLACEMENT = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier(MODID, "stellar_promise_ore_placement"));
    //itemGroup
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(LIFT))
            .displayName(Text.translatable("itemGroup.sakura_server.item_group"))
            .entries((context, entries) -> {
/*                for (Item allModItem : getAllModItems()) {
                    entries.add(new ItemStack(allModItem));
                }*/
                entries.add(new ItemStack(LIFT));
                entries.add(new ItemStack(ANGEL_BLOCK));
                entries.add(new ItemStack(SORTER));
                entries.add(new ItemStack(EMERALD_APPLE));
                entries.add(new ItemStack(SOUL_GEM));
                entries.add(new ItemStack(DRAGON_SWORD));
                entries.add(new ItemStack(STARDUST_WAND));
                entries.add(new ItemStack(GAMBLING_PICKAXE));
                entries.add(new ItemStack(ANTIMATTER_PICKAXE));
                entries.add(new ItemStack(MOVE_VILLAGER));
                entries.add(new ItemStack(MAGNET));
                entries.add(new ItemStack(ANGEL_WINGS_CORE));
                entries.add(new ItemStack(SACRED_FEATHERS));
                entries.add(new ItemStack(COMPRESSED_COPPER_1));
                entries.add(new ItemStack(COMPRESSED_COPPER_2));
                entries.add(new ItemStack(COMPRESSED_COPPER_3));
                entries.add(new ItemStack(COMPRESSED_COPPER_4));
                entries.add(new ItemStack(COMPRESSED_COPPER_5));
                entries.add(new ItemStack(COMPRESSED_COPPER_6));
                entries.add(new ItemStack(COMPRESSED_COPPER_7));
                entries.add(new ItemStack(COMPRESSED_COPPER_8));
                entries.add(new ItemStack(COMPRESSED_COPPER_9));
                entries.add(new ItemStack(ANGEL_BLOCK_FRAMEWORK));
                entries.add(new ItemStack(DRAW_SOMETHING));
                entries.add(new ItemStack(DRAW_BLUE_SOMETHING));
                entries.add(new ItemStack(DRAW_PURPLE_SOMETHING));
                entries.add(new ItemStack(DRAW_GOLDEN_SOMETHING));
                entries.add(new ItemStack(DREAM_KEY));
                entries.add(new ItemStack(TEN_DREAM_KEY));
                entries.add(new ItemStack(STELLAR_PROMISE_ORE));
                entries.add(new ItemStack(STELLAR_PROMISE));
                entries.add(new ItemStack(TEN_STELLAR_PROMISE));
                entries.add(new ItemStack(REPLICATORS));
                entries.add(new ItemStack(VAST_STAR));
                entries.add(new ItemStack(TIMESPACE_SHARDS));
                entries.add(new ItemStack(TIMESPACE_GEM));
                entries.add(new ItemStack(TIMESPACE_ORE));
                entries.add(new ItemStack(TIMESPACE_SHOVEL));
                entries.add(new ItemStack(TIMESPACE_PICKAXE));
                entries.add(new ItemStack(TIMESPACE_HOE));
                entries.add(new ItemStack(TIMESPACE_AXE));
                entries.add(new ItemStack(TIMESPACE_SWORD));
                entries.add(new ItemStack(DEATH_SCROLL));
                entries.add(new ItemStack(NETHERITE_HAMMER));
                entries.add(new ItemStack(TEST_ITEM));
                for (ItemStack allModEnchantmentBook : getAllModEnchantmentBooks()) {
                    entries.add(allModEnchantmentBook);
                }
            })
            .build();
    public static boolean hasGeyser = FabricLoader.getInstance().isModLoaded("geyser-fabric");
    public static boolean hasFloodgate = FabricLoader.getInstance().isModLoaded("floodgate");
    public static BlockEntityType<AngelBlockEntity> ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;
    public static BlockEntityType<SorterBlockEntity> SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;

    //specialRecipe
    public static RecipeSerializer<TimespaceUpdateRecipe> TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER;
    public static void printDebugLog(String log) {
        // prevent usage if the Instance is not run in a development environment
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        // customize that message however you want...
        LOGGER.info("[DEBUG][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

    @Override
    public void onInitialize() {
        //reg
        //test
        Registry.register(Registries.ITEM, new Identifier(MODID, "test_item"), TEST_ITEM);
        //block
        Registry.register(Registries.BLOCK, new Identifier(MODID, "lift"), LIFT);
        Registry.register(Registries.BLOCK, new Identifier(MODID, "angel_block"), ANGEL_BLOCK);
        ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MODID, "angel_block_entity"),
                FabricBlockEntityTypeBuilder.create(AngelBlockEntity::new, ANGEL_BLOCK).build()
        );
        Registry.register(Registries.BLOCK, new Identifier(MODID, "sorter"), SORTER);
        SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MODID, "sorter_block_entity"),
                FabricBlockEntityTypeBuilder.create(SorterBlockEntity::new, SORTER).build()
        );
        Registry.register(Registries.BLOCK, new Identifier(MODID, "timespace_ore"), TIMESPACE_ORE);
        Registry.register(Registries.BLOCK, new Identifier(MODID, "stellar_promise_ore"), STELLAR_PROMISE_ORE);
        //item
        Registry.register(Registries.ITEM, new Identifier(MODID, "lift"), new SakuraBlockItem(LIFT, new FabricItemSettings(), 2));
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_block"), new SakuraBlockItem(ANGEL_BLOCK, new FabricItemSettings().fireproof(), 5));
        Registry.register(Registries.ITEM, new Identifier(MODID, "sorter"),
                new SakuraBlockItem(SORTER, new FabricItemSettings(), 2));
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_ore"), new SakuraBlockItem(TIMESPACE_ORE, new Item.Settings().fireproof(), 3));
        Registry.register(Registries.ITEM, new Identifier(MODID, "stellar_promise_ore"), new SakuraBlockItem(STELLAR_PROMISE_ORE, new Item.Settings().fireproof(), 5));
        Registry.register(Registries.ITEM, new Identifier(MODID, "emerald_apple"), EMERALD_APPLE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "soul_gem"), SOUL_GEM);
        Registry.register(Registries.ITEM, new Identifier(MODID, "villager_mover"), MOVE_VILLAGER);
        Registry.register(Registries.ITEM, new Identifier(MODID, "dragon_sword"), DRAGON_SWORD);
        Registry.register(Registries.ITEM, new Identifier(MODID, "stardust_wand"), STARDUST_WAND);
        Registry.register(Registries.ITEM, new Identifier(MODID, "magnet"), MAGNET);
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_wings_core"), ANGEL_WINGS_CORE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "sacred_feathers"), SACRED_FEATHERS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_1"), COMPRESSED_COPPER_1);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_2"), COMPRESSED_COPPER_2);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_3"), COMPRESSED_COPPER_3);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_4"), COMPRESSED_COPPER_4);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_5"), COMPRESSED_COPPER_5);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_6"), COMPRESSED_COPPER_6);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_7"), COMPRESSED_COPPER_7);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_8"), COMPRESSED_COPPER_8);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_9"), COMPRESSED_COPPER_9);
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_block_framework"), ANGEL_BLOCK_FRAMEWORK);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_something"), DRAW_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_blue_something"), DRAW_BLUE_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_purple_something"), DRAW_PURPLE_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_golden_something"), DRAW_GOLDEN_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "dream_key"), DREAM_KEY);
        Registry.register(Registries.ITEM, new Identifier(MODID, "ten_dream_key"), TEN_DREAM_KEY);
        Registry.register(Registries.ITEM, new Identifier(MODID, "stellar_promise"), STELLAR_PROMISE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "ten_stellar_promise"), TEN_STELLAR_PROMISE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "gambling_pickaxe"), GAMBLING_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "antimatter_pickaxe"), ANTIMATTER_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "replicators"), REPLICATORS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "vast_star"), VAST_STAR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_shards"), TIMESPACE_SHARDS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_gem"), TIMESPACE_GEM);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_sword"), TIMESPACE_SWORD);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_axe"), TIMESPACE_AXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_pickaxe"), TIMESPACE_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_shovel"), TIMESPACE_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_hoe"), TIMESPACE_HOE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "death_scroll"), DEATH_SCROLL);
        Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_hammer"), NETHERITE_HAMMER);
        //enchantment
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "soul_bound"), SOUL_BOUND);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "vein_mine"), VEIN_MINE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_sharpness"), ADVANCED_SHARPNESS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_smite"), ADVANCED_SMITE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_bane_of_arthropods"), ADVANCED_BANE_OF_ARTHROPODS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_power"), ADVANCED_POWER);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "education"), EDUCATION);
        //itemGroup
        Registry.register(Registries.ITEM_GROUP, new Identifier(MODID, "item_group"), ITEM_GROUP);
        //effect
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "lift_cd"), LIFT_CD);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "dragon_sword_effect"), DRAGON_SWORD_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "healbane"), HEALBANE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "angel_wings"), ANGEl_WINGS);
        //recipe
        TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER = RecipeSerializer.register("timespace_update",new SpecialRecipeSerializer<>(TimespaceUpdateRecipe::new));
/*        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(MODID,"timespace_update"),TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER);*/
        //statistics
        Registry.register(Registries.CUSTOM_STAT, "highest_damage", HIGHEST_DAMAGE);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "highest_hurt", HIGHEST_HURT);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "lift_all", LIFT_ALL);
        Stats.CUSTOM.getOrCreateStat(LIFT_ALL, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "lift_up", LIFT_UP);
        Stats.CUSTOM.getOrCreateStat(LIFT_UP, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "lift_down", LIFT_DOWN);
        Stats.CUSTOM.getOrCreateStat(LIFT_DOWN, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "vein_mine_count", VEIN_MINE_COUNT);
        Stats.CUSTOM.getOrCreateStat(VEIN_MINE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_count", DRAW_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_golden_count", DRAW_GOLDEN_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_GOLDEN_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_purple_count", DRAW_PURPLE_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_PURPLE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_blue_count", DRAW_BLUE_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_BLUE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "break_block_count", BREAK_BLOCK_COUNT);
        Stats.CUSTOM.getOrCreateStat(BREAK_BLOCK_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "total_damage_taken", TOTAL_DAMAGE_TAKEN);
        Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_TAKEN, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_online_time", TOTAL_ONLINE_TIME);
        Stats.CUSTOM.getOrCreateStat(TOTAL_ONLINE_TIME, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "total_damage_caused", TOTAL_DAMAGE_CAUSED);
        Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_CAUSED, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_player_damage_taken", TOTAL_PLAYER_DAMAGE_TAKEN);
        Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_TAKEN, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_player_damage_caused", TOTAL_PLAYER_DAMAGE_CAUSED);
        Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_CAUSED, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "place_block_count", PLACE_BLOCK_COUNT);
        Stats.CUSTOM.getOrCreateStat(PLACE_BLOCK_COUNT, StatFormatter.DEFAULT);
        //sound
        Registry.register(Registries.SOUND_EVENT, DRAGON_ROAR, DRAGON_ROAR_SOUND);
        Registry.register(Registries.SOUND_EVENT, MAGNET_USE, MAGNET_USE_SOUND);
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
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                STELLAR_PROMISE_ORE_PLACEMENT);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                TIMESPACE_ORE_PLACEMENT);
        printDebugLog("加载成功!");
        printDebugLog("是否加载Geyser:" + hasGeyser);
        printDebugLog("是否加载floodgate:" + hasFloodgate);
    }


}