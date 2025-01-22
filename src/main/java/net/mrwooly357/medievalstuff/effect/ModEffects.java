package net.mrwooly357.medievalstuff.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> REACH = registerStatusEffect("reach",
            new ReachStatusEffect(StatusEffectCategory.BENEFICIAL, 14270531)
                    .addAttributeModifier(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                            Identifier.of(MedievalStuff.MOD_ID, "reach"),
                            1,
                            EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            Identifier.of(MedievalStuff.MOD_ID, "reach"),
                            1,
                            EntityAttributeModifier.Operation.ADD_VALUE));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MedievalStuff.MOD_ID, name), statusEffect);
    }

    public static void registerEffects()  {
        MedievalStuff.LOGGER.info("Registering Mod Effects for " + MedievalStuff.MOD_ID);
    }
}
