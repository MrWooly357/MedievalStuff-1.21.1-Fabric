package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

/**
 * A hybrid weapon family contains different stats which are used by an {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem} item.
 * <p>
 * To view the hybrid weapon families from Medieval Stuff, visit {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamilies}.
 */
public class HybridWeaponFamily {

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
    String name;

    public HybridWeaponFamily() {}


    public HybridWeaponFamily attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getAttackDamage() {
        return attackDamage;
    }

    public HybridWeaponFamily attackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getAttackSpeed() {
        return attackSpeed;
    }

    public HybridWeaponFamily attackCriticalChance(float attackCriticalChance) {
        this.attackCriticalChance = attackCriticalChance;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getAttackCriticalChance() {
        return attackCriticalChance;
    }

    public HybridWeaponFamily additionalAttackKnockback(float additionalAttackKnockback) {
        this.additionalAttackKnockback = additionalAttackKnockback;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getAdditionalAttackKnockback() {
        return additionalAttackKnockback;
    }

    public HybridWeaponFamily additionalAttackRange(float additionalAttackRange) {
        this.additionalAttackRange = additionalAttackRange;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getAdditionalAttackRange() {
        return additionalAttackRange;
    }

    public HybridWeaponFamily chargeTime(int chargeTime) {
        this.chargeTime = chargeTime;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public int getChargeTime() {
        return chargeTime;
    }

    public HybridWeaponFamily throwPower(float throwPower) {
        this.throwPower = throwPower;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getThrowPower() {
        return throwPower;
    }

    public HybridWeaponFamily throwAccuracy(float throwAccuracy) {
        this.throwAccuracy = throwAccuracy;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getThrowAccuracy() {
        return throwAccuracy;
    }

    public HybridWeaponFamily throwCriticalChance(float throwCriticalChance) {
        this.throwCriticalChance = throwCriticalChance;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getThrowCriticalChance() {
        return throwCriticalChance;
    }
    
    public HybridWeaponFamily projectileGravity(float projectileGravity) {
        this.projectileGravity = projectileGravity;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public float getProjectileGravity() {
        return projectileGravity;
    }

    public HybridWeaponFamily durability(int durability) {
        this.durability = durability;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public int getDurability() {
        return durability;
    }

    public HybridWeaponFamily name(String name) {
        this.name = name;

        return this;
    }

    /**
     * @return the {@link net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily}.
     */
    public String getTranslationKey(String modId) {
        return "hybrid_weapon_family." + modId + "." + name;
    }
}
