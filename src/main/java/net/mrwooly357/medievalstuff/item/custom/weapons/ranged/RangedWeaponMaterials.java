package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.mrwooly357.medievalstuff.item.ModItems;

import java.util.function.Supplier;

public enum RangedWeaponMaterials implements RangedWeaponMaterial {
    COPPER(128, 1.5F, 2.0F, 0.1F, 0.00F, 7, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    IRON(192, 1.45F, 2.25F, 0.15F,0.15F, 8, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    SILVER(256, 1.4F, 2.5F, 0.2F,0.20F, 9, () -> Ingredient.ofItems(ModItems.SILVER_INGOT)),
    NETHERITE(1024, 0.75F, 5.0F, 0.5F,0.50F, 20, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final int baseDurability;
    private final float baseChargeTime;
    private final float baseShotPower;
    private final float baseAccuracy;
    private final float baseCriticalChance;
    private final int baseRange;
    private final Supplier<Ingredient> repairIngredient;

    RangedWeaponMaterials(int baseDurability, float baseChargeTime, float baseShotPower, float baseAccuracy, float baseCriticalChance, int baseRange, Supplier<Ingredient> repairIngredient) {
        this.baseDurability = baseDurability;
        this.baseChargeTime = baseChargeTime;
        this.baseShotPower = baseShotPower;
        this.baseAccuracy = baseAccuracy;
        this.baseCriticalChance = baseCriticalChance;
        this.baseRange = baseRange;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getBaseDurability() {
        return baseDurability;
    }

    @Override
    public float getBaseChargeTime() {
        return baseChargeTime;
    }

    @Override
    public float getBaseShotPower() {
        return baseShotPower;
    }

    @Override
    public float getBaseAccuracy() {
        return baseAccuracy;
    }

    @Override
    public float getBaseCriticalChance() {
        return baseCriticalChance;
    }

    @Override
    public int getBaseRange() {
        return baseRange;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
