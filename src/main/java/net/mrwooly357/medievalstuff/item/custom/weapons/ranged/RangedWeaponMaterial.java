package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

import net.minecraft.recipe.Ingredient;

public interface RangedWeaponMaterial {

    int getBaseDurability();

    float getBaseChargeTime();

    float getBaseShotPower();

    float getBaseAccuracy();

    float getBaseCriticalChance();

    int getBaseRange();

    Ingredient getRepairIngredient();
}
