package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.mrwooly357.medievalstuff.util.InDevelopment;

/**
 * A hybrid weapon class contains different stats which are used by an {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem} item.
 * <p>
 * To view the hybrid weapon classes from Medieval Stuff, visit {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClasses}.
 */
public class HybridWeaponClass implements AbstractHybridWeaponClass {

    float attackDamage;
    float attackSpeed;
    @InDevelopment(since = "1.0.0-alpha-1.21.1-fabric", reason = "I'll just add it later")
    float attackCriticalChance;
    float additionalAttackKnockback;
    float additionalAttackRange;
    int chargeTime;
    float throwPower;
    float throwAccuracy;
    @InDevelopment(since = "1.0.0-alpha-1.21.1-fabric", reason = "I'll just add it later")
    float throwCriticalChance;
    float projectileGravity;
    int durability;
    HybridWeaponFamily hybridWeaponFamily;
    String name;
    String translationModId;

    public HybridWeaponClass() {}


    public HybridWeaponClass attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;

        return this;
    }

    public HybridWeaponClass attackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;

        return this;
    }

    @InDevelopment(since = "1.0.0-alpha-1.21.1-fabric", reason = "I'll just add it later")
    public HybridWeaponClass attackCriticalChance(float attackCriticalChance) {
        this.attackCriticalChance = attackCriticalChance;

        return this;
    }

    public HybridWeaponClass additionalAttackKnockback(float additionalAttackKnockback) {
        this.additionalAttackKnockback = additionalAttackKnockback;

        return this;
    }

    public HybridWeaponClass additionalAttackRange(float additionalAttackRange) {
        this.additionalAttackRange = additionalAttackRange;

        return this;
    }

    public HybridWeaponClass chargeTime(int chargeTime) {
        this.chargeTime = chargeTime;

        return this;
    }

    public HybridWeaponClass throwPower(float throwPower) {
        this.throwPower = throwPower;

        return this;
    }

    public HybridWeaponClass throwAccuracy(float throwAccuracy) {
        this.throwAccuracy = throwAccuracy;

        return this;
    }

    @InDevelopment(since = "1.0.0-alpha-1.21.1-fabric", reason = "I'll just add it later")
    public HybridWeaponClass throwCriticalChance(float throwCriticalChance) {
        this.throwCriticalChance = throwCriticalChance;

        return this;
    }

    public HybridWeaponClass projectileGravity(float projectileGravity) {
        this.projectileGravity = projectileGravity;

        return this;
    }

    public HybridWeaponClass durability(int durability) {
        this.durability = durability;

        return this;
    }

    public HybridWeaponClass hybridWeaponFamily(HybridWeaponFamily hybridWeaponFamily) {
        this.hybridWeaponFamily = hybridWeaponFamily;

        return this;
    }

    public HybridWeaponClass name(String name) {
        this.name = name;

        return this;
    }

    public HybridWeaponClass translationModId(String translationModId) {
        this.translationModId = translationModId;

        return this;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public float getAttackCriticalChance() {
        return attackCriticalChance;
    }

    @Override
    public float getAdditionalAttackKnockback() {
        return additionalAttackKnockback;
    }

    @Override
    public float getAdditionalAttackRange() {
        return additionalAttackRange;
    }

    @Override
    public int getChargeTime() {
        return chargeTime;
    }

    @Override
    public float getThrowPower() {
        return throwPower;
    }

    @Override
    public float getThrowAccuracy() {
        return throwAccuracy;
    }

    @Override
    public float getThrowCriticalChance() {
        return throwCriticalChance;
    }

    @Override
    public float getProjectileGravity() {
        return projectileGravity;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public HybridWeaponFamily getHybridWeaponfamily() {
        return hybridWeaponFamily;
    }

    public String getTranslationKey() {
        return "hybrid_weapon_class." + translationModId + "." + name;
    }
}
