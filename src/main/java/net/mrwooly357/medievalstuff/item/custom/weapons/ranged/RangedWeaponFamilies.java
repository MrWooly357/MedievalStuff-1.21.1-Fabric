package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

public enum RangedWeaponFamilies implements RangedWeaponFamily {
    BOWS(16, 0.1F, 0.2F, 0.75F, 0.05F, 1),
    CROSSBOWS(32, 0.25F, 0.3F, 0.9F, 0.05F, 2);

    private final int extraDurability;
    private final float extraChargeTime;
    private final float extraShotPower;
    private final float extraAccuracy;
    private final float extraCriticalChance;
    private final int extraRange;

    RangedWeaponFamilies(int extraDurability, float extraChargeTime, float extraShotPower, float extraAccuracy, float extraCriticalChance, int extraRange) {
        this.extraDurability = extraDurability;
        this.extraChargeTime = extraChargeTime;
        this.extraShotPower = extraShotPower;
        this.extraAccuracy = extraAccuracy;
        this.extraCriticalChance = extraCriticalChance;
        this.extraRange = extraRange;
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
}
