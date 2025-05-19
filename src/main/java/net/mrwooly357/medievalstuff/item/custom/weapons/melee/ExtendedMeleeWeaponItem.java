package net.mrwooly357.medievalstuff.item.custom.weapons.melee;

import net.minecraft.item.Item;

public abstract class ExtendedMeleeWeaponItem extends Item {

    public ExtendedMeleeWeaponItem(Settings settings) {
        super(settings);
    }


    public boolean hasSweepingAttack() {
        return false;
    }
}
