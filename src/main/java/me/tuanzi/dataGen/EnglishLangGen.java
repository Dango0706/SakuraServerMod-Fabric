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
        translationBuilder.add(LIFT, "Lift");
        //item
        translationBuilder.add(EMERALD_APPLE,"Emerald Apple");
        translationBuilder.add(SOUL_GEM,"Soul Gem");
        translationBuilder.add(DRAGON_SWORD,"Dragon Sword");
        translationBuilder.add(STARDUST_WAND, "Stardust Wand");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_owner", "You don't own him, you can't bind him!");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_tamed", "He's not tamed yet! Unable to bind");
        translationBuilder.add("item.sakura_server.soul_gem.fail.has_entity", "This SoulGem already has a binding!");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_found", "There are no creatures bound to you within 40 squares~");
        translationBuilder.add("item.sakura_server.soul_gem.respawn", "Resurrect! My love!!!");
        translationBuilder.add("item.sakura_server.soul_gem.found", "Your dog is in x:%d,y:%d,z:%d ♪ He's shining ♪");
        translationBuilder.add("item.sakura_server.soul_gem.uuid", "bound creature,UUID:");
        translationBuilder.add("item.sakura_server.soul_gem.dead", "The creature you bound is dead!!His SoulGem sparkles....");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc1", "Skill:Dragon Roar");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc2", "Right click to use,CD:15s,Duration:10s");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc3", "Deplete 30% of your current life value to gain Dragon's Roar.");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc4", "Dragon Roar: +4 Attack Power, +80% Attack Speed, +30% Movement Speed");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc5", "Slowly restores 20% of your maximum health.");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc6", "Invalidated by switching weapons.");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc1", "Skill:Starlight Power");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc2", "Right click to use,CD:30s,Duration:10s");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc3", "Grants 10s of broken healing to creatures in a 5 square radius, and deals 8 points of damage.");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc4", "Restore 8 life points to your pet!");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc5", "Crouching then works on other players and other players' pets as well!");
        //enchantment
        translationBuilder.add(SOUL_BOUND, "§6SoulBound");
        translationBuilder.add(VEIN_MINE, "§aVeinMine");
        translationBuilder.add("enchantment.sakura_server.vein_mine.need_food", "You're hungry. Go get something to eat and chain mine!");
        //effect
        translationBuilder.add(LIFT_CD, "lift CD");
        translationBuilder.add(DRAGON_SWORD_EFFECT, "Dragon Rage");
        translationBuilder.add(HEALBANE, "Break healing");
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
