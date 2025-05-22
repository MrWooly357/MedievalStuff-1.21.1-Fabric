package net.mrwooly357.medievalstuff.entity.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.effect.custom.ReachStatusEffect;
import net.mrwooly357.medievalstuff.entity.effect.custom.SoulDecayStatusEffect;
import net.mrwooly357.medievalstuff.entity.effect.custom.SoulProtectionStatusEffect;
import net.mrwooly357.medievalstuff.registry.StatusEffectRegistryHelper;

public class ModStatusEffects {

    public static final RegistryEntry<StatusEffect> REACH = register(
            "reach", new ReachStatusEffect(
                    StatusEffectCategory.BENEFICIAL, 14270531
            )
                    .addAttributeModifier(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, Identifier.of(MedievalStuff.MOD_ID, "effect.reach"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, Identifier.of(MedievalStuff.MOD_ID, "effect.reach"), 1.0F, EntityAttributeModifier.Operation.ADD_VALUE)
    );
    public static final RegistryEntry<StatusEffect> SOUL_DECAY = register(
            "soul_decay", new SoulDecayStatusEffect(
                    StatusEffectCategory.HARMFUL, 11410521
            )
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -0.05F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -1.0F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -0.15F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_ARMOR, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -1.0F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -1.0F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_MAX_ABSORPTION, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -2.0F, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, Identifier.of(MedievalStuff.MOD_ID, "effect.soul_decay"), -0.2F, EntityAttributeModifier.Operation.ADD_VALUE)
    );
    public static final RegistryEntry<StatusEffect> SOUL_PROTECTION = register(
            "soul_protection", new SoulProtectionStatusEffect(
                    StatusEffectCategory.BENEFICIAL, 4643041
            )
    );


    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
        return StatusEffectRegistryHelper.register(Identifier.of(MedievalStuff.MOD_ID, name), statusEffect);
    }

    public static void init()  {
        MedievalStuff.LOGGER.info("Initializing " + MedievalStuff.MOD_ID + "status effects");
    }
}
