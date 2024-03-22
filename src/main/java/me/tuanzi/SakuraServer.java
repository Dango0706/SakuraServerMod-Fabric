package me.tuanzi;

import me.tuanzi.enchantments.SoulBound;
import me.tuanzi.enchantments.VeinMine;
import me.tuanzi.utils.CommandRegister;
import me.tuanzi.utils.EventRegister;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static me.tuanzi.network.C2SChannel.registerC2SPackets;

public class SakuraServer implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("SakuraServer");

    public static final String MODID = "sakura_server";

    public static Enchantment SOUL_BOUND = new SoulBound(Enchantment.Rarity.RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment VEIN_MINE = new VeinMine(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        //reg
        //enchantment
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "soul_bound"), SOUL_BOUND);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "vein_mine"), VEIN_MINE);

        //event
		new EventRegister();
        //command
        new CommandRegister();
        //network
        registerC2SPackets();
    }
}