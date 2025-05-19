package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

/**
 * A hybrid weapon material contains different stats which are used by an {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem} item.
 * <p>
 * To view the hybrid weapon materials from Medieval Stuff, visit {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterials}.
 */
public class HybridWeaponMaterial {

    private float attackDamage;
    private float attackSpeed;
    private float attackCriticalChance;
    private float additionalAttackKnockback;
    private float additionalAttackRange;
    private int chargeTime;
    private float throwPower;
    private float throwAccuracy;
    private float throwCriticalChance;
    private float projectileGravity;
    private int durability;
    private int enchantability;
    private Supplier<Ingredient> repairIngredient;
    private String name;
    private String translationModId;

    public HybridWeaponMaterial() {}


    public HybridWeaponMaterial attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#attackDamage}.
     */
    public float getAttackDamage() {
        return attackDamage;
    }

    public HybridWeaponMaterial attackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#attackSpeed}.
     */
    public float getAttackSpeed() {
        return attackSpeed;
    }

    public HybridWeaponMaterial attackCriticalChance(float attackCriticalChance) {
        this.attackCriticalChance = attackCriticalChance;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#attackCriticalChance}.
     */
    public float getAttackCriticalChance() {
        return attackCriticalChance;
    }

    public HybridWeaponMaterial additionalAttackKnockback(float additionalAttackKnockback) {
        this.additionalAttackKnockback = additionalAttackKnockback;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#additionalAttackRange}.
     */
    public float getAdditionalAttackKnockback() {
        return additionalAttackKnockback;
    }

    public HybridWeaponMaterial additionalAttackRange(float additionalAttackRange) {
        this.additionalAttackRange = additionalAttackRange;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#additionalAttackRange}.
     */
    public float getAdditionalAttackRange() {
        return additionalAttackRange;
    }

    public HybridWeaponMaterial chargeTime(int chargeTime) {
        this.chargeTime = chargeTime;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#chargeTime}.
     */
    public int getChargeTime() {
        return chargeTime;
    }

    public HybridWeaponMaterial throwPower(float throwPower) {
        this.throwPower = throwPower;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#throwPower}.
     */
    public float getThrowPower() {
        return throwPower;
    }

    public HybridWeaponMaterial throwAccuracy(float throwAccuracy) {
        this.throwAccuracy = throwAccuracy;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#throwAccuracy}.
     */
    public float getThrowAccuracy() {
        return throwAccuracy;
    }

    public HybridWeaponMaterial throwCriticalChance(float throwCriticalChance) {
        this.throwCriticalChance = throwCriticalChance;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#throwCriticalChance}.
     */
    public float getThrowCriticalChance() {
        return throwCriticalChance;
    }
    
    public HybridWeaponMaterial projectileGravity(float projectileGravity) {
        this.projectileGravity = projectileGravity;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#projectileGravity}.
     */
    public float getProjectileGravity() {
        return projectileGravity;
    }

    public HybridWeaponMaterial durability(int durability) {
        this.durability = durability;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#durability}.
     */
    public int getDurability() {
        return durability;
    }

    public HybridWeaponMaterial enchantability(int enchantability) {
        this.enchantability = enchantability;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#enchantability}.
     */
    public int getEnchantability() {
        return enchantability;
    }

    public HybridWeaponMaterial repairIngredient(Supplier<Ingredient> repairIngredient) {
        this.repairIngredient = repairIngredient;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial#repairIngredient}.
     */
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    public HybridWeaponMaterial name(String name) {
        this.name = name;

        return this;
    }

    public void translationModId(String translationModId) {
        this.translationModId = translationModId;

    }

    public String getTranslationKey() {
        return "hybrid_weapon_material." + translationModId + "." + name;
    }
}
