package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

/**
 * A hybrid weapon class contains different stats which are used by an {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem} item.
 * <p>
 * To view the hybrid weapon classes from Medieval Stuff, visit {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClasses}.
 */
public class HybridWeaponClass {

    float attackDamage;
    float attackSpeed;
    float attackCriticalChance;
    float additionalAttackKnockback;
    float additionalAttackRange;
    int chargeTime;
    float throwPower;
    float throwAccuracy;
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

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getAttackCriticalChance() {
        return attackCriticalChance;
    }

    public float getAdditionalAttackKnockback() {
        return additionalAttackKnockback;
    }

    public float getAdditionalAttackRange() {
        return additionalAttackRange;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    public float getThrowPower() {
        return throwPower;
    }

    public float getThrowAccuracy() {
        return throwAccuracy;
    }

    public float getThrowCriticalChance() {
        return throwCriticalChance;
    }

    public float getProjectileGravity() {
        return projectileGravity;
    }

    public int getDurability() {
        return durability;
    }

    public HybridWeaponFamily getHybridWeaponfamily() {
        return hybridWeaponFamily;
    }

    public String getTranslationKey() {
        return "hybrid_weapon_class." + translationModId + "." + name;
    }
}
