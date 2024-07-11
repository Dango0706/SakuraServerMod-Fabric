package me.tuanzi.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class EffectRegistry {


    //effect
    public static final StatusEffect LIFT_CD = new LiftCDEffect();
    public static final StatusEffect DRAGON_SWORD_EFFECT = new DragonSwordEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "775D699C-A100-5520-0E0D-7DEC4BDD99AB", 4.0f, EntityAttributeModifier.Operation.ADDITION)
            .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "F7589552-C50D-3067-C438-1EFEDBD53D8B", 0.5f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "F7589552-C50D-3067-C438-1EFEDBD53D8B", 0.3f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final StatusEffect HEALBANE = new Healbane();
    public static final StatusEffect ANGEl_WINGS = new AngelWings();

    public EffectRegistry() {
        printDebugLog("加载状态效果...");
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "lift_cd"), LIFT_CD);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "dragon_sword_effect"), DRAGON_SWORD_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "healbane"), HEALBANE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "angel_wings"), ANGEl_WINGS);

    }
}
