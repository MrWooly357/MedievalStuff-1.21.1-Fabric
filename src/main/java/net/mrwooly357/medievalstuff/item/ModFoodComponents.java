package net.mrwooly357.medievalstuff.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {

    public static final FoodComponent PIECE_OF_JELLY = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1F)
            .statusEffect(
                    new StatusEffectInstance(StatusEffects.LEVITATION, 10), 0.2F
            )
            .snack()
            .build();
    public static final FoodComponent JAR_OF_JELLY = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.3F)
            .statusEffect(
                    new StatusEffectInstance(StatusEffects.LEVITATION, 50), 0.4F
            )
            .build();
}
