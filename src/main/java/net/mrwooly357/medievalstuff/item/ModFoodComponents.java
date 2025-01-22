package net.mrwooly357.medievalstuff.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PIECE_OF_JELLY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20), 0.25F).snack().build();
    public static final FoodComponent JAR_OF_JELLY = new FoodComponent.Builder().nutrition(8).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100), 0.75F).build();
}
