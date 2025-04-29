package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

public enum ModRangedWeaponSubclasses implements RangedWeaponSubclass {
    ADVANCED_BOWS_SHORT(8, -0.95F, 0.25F, 0.05F,0.00F, 1, ModRangedWeaponClasses.ADVANCED_BOWS),
    ADVANCED_BOWS_LONG(16, 0.25F, 0.5F, 0.15F,0.05F, 2, ModRangedWeaponClasses.ADVANCED_BOWS),
    CROSSBAUTOES_LIGHT(32, 0.25F, 0.5F, 0.1F,0.05F, 2, ModRangedWeaponClasses.CROSSBAUTOES),
    CROSSBAUTOES_HEAVY(64, 0.5F, 1.0F, 0.2F,0.10F, 3, ModRangedWeaponClasses.CROSSBAUTOES);

    private final int extraDurability;
    private final float extraChargeTime;
    private final float extraShotPower;
    private final float extraAccuracy;
    private final float extraCriticalChance;
    private final int extraRange;
    private final RangedWeaponClass rangedWeaponClass;

    ModRangedWeaponSubclasses(int extraDurability, float extraChargeTime, float extraShotPower, float extraAccuracy, float extraCriticalChance, int extraRange, RangedWeaponClass rangedWeaponClass) {
        this.extraDurability = extraDurability;
        this.extraChargeTime = extraChargeTime;
        this.extraShotPower = extraShotPower;
        this.extraAccuracy = extraAccuracy;
        this.extraCriticalChance = extraCriticalChance;
        this.extraRange = extraRange;
        this.rangedWeaponClass = rangedWeaponClass;
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
    public RangedWeaponClass getRangedWeaponClass() {
        return rangedWeaponClass;
    }
}
