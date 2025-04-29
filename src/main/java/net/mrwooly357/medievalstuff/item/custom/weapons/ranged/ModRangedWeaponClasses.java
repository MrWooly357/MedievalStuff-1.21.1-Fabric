package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

public enum ModRangedWeaponClasses implements RangedWeaponClass {
    ADVANCED_BOWS(16, 0.1F, 0.3F, 0.1F, 0.00F, 1, ModRangedWeaponFamilies.BOWS),
    CROSSBAUTOES(16, 0.2F, 0.5F, 0.15F, 0.025F, 1, ModRangedWeaponFamilies.CROSSBOWS);

    private final int extraDurability;
    private final float extraChargeTime;
    private final float extraShotPower;
    private final float extraAccuracy;
    private final float extraCriticalChance;
    private final int extraRange;
    private final RangedWeaponFamily rangedWeaponFamily;

    ModRangedWeaponClasses(int extraDurability, float extraChargeTime, float extraShotPower, float extraAccuracy, float extraCriticalChance, int extraRange, RangedWeaponFamily rangedWeaponFamily) {
        this.extraDurability = extraDurability;
        this.extraChargeTime = extraChargeTime;
        this.extraShotPower = extraShotPower;
        this.extraAccuracy = extraAccuracy;
        this.extraCriticalChance = extraCriticalChance;
        this.extraRange = extraRange;
        this.rangedWeaponFamily = rangedWeaponFamily;
    }


    @Override
    public int getExtraDurability() {
        return extraDurability;
    }

    @Override
    public float getExtraChargeTime() {
        return extraChargeTime;
    }

    @Override
    public float getExtraShotPower() {
        return extraShotPower;
    }

    @Override
    public float getExtraAccuracy() {
        return extraAccuracy;
    }

    @Override
    public float getExtraCriticalChance() {
        return extraCriticalChance;
    }

    @Override
    public int getExtraRange() {
        return extraRange;
    }

    @Override
    public RangedWeaponFamily getRangedWeaponFamily() {
        return rangedWeaponFamily;
    }
}
