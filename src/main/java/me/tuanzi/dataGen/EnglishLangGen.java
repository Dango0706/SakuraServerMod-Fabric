package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static me.tuanzi.SakuraServer.*;

public class EnglishLangGen extends FabricLanguageProvider {

    public EnglishLangGen(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    /**
     * Implement this method to register languages.
     *
     * <p>Call {@link TranslationBuilder#add(String, String)} to add a translation.
     *
     * @param translationBuilder
     */
    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //block
        translationBuilder.add(LIFT, "lift");
        //item
        translationBuilder.add(EMERALD_APPLE,"EmeraldApple");
        //enchantment
        translationBuilder.add(SOUL_BOUND, "§6SoulBound");
        translationBuilder.add(VEIN_MINE, "§aVeinMine");
        translationBuilder.add("enchantment.sakura_server.vein_mine.need_food", "You're hungry. Go get something to eat and chain mine!");
        //effect
        translationBuilder.add(LIFT_CD, "lift CD");
        //itemGroup
        translationBuilder.add("itemGroup.sakura_server.item_group", "Sakura Server");
        //command
        translationBuilder.add("commands.sakura_server.error.not_player", "This command is not issued by the player.");
        translationBuilder.add("commands.sakura_server.removeLore.ok", "Successfully cleared the lore information of %d item.");
        //keyBind
        translationBuilder.add("key.category.sakura_server", "Sakura Server");
        translationBuilder.add("key.sakura_server.vein_mine", "VeinMine");
        //statistics
        translationBuilder.add("stat.sakura_server.highest_damage", "Deals the highest amount of damage at once");
        translationBuilder.add("stat.sakura_server.highest_hurt", "Highest single damage taken");
        translationBuilder.add("stat.sakura_server.lift_all", "Total number of times the elevator was used");
        translationBuilder.add("stat.sakura_server.lift_up", "Number of elevator rises used");
        translationBuilder.add("stat.sakura_server.lift_down", "Number of elevator descents used");
        translationBuilder.add("stat.sakura_server.vein_mine_count", "Total number of vein mine cubes");
    }
}
