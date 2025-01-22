package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.MathHelper;
import net.mrwooly357.wool_lib.items.weapons.AdvancedSweepMeleeWeaponItem;

public class TheGreatPaladinsClaymoreItem extends AdvancedSweepMeleeWeaponItem {
    public TheGreatPaladinsClaymoreItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        if (this.getDistanceBetweenEntities(target, attacker) > 3.25F) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 0, false, true), attacker);
            target.damage(target.getDamageSources().generic(), 1F);
        }
    }

    /**
     *
     * @param target is a point used as a minuend.
     * @param attacker is a point used as a subtrahend.
     * @return gives you a value as a float which represents the distance between target and attacker.
     * Note: The value might be not accurate. But it's still very close, so I decided to use it.
     */
    private double getDistanceBetweenEntities(LivingEntity target, LivingEntity attacker) {
        double rawXDistance = target.getX() - attacker.getX();
        double rawYDistance = target.getY() - attacker.getY();
        double rawZDistance = target.getZ() - attacker.getZ();

        double xDistance;
        double yDistance;
        double zDistance;

        if (rawXDistance < 0 ) {
            xDistance = opposite(rawXDistance);
        } else {
            xDistance = rawXDistance;
        }

        if (rawYDistance < 0 ) {
            yDistance = opposite(rawYDistance);
        } else {
            yDistance = rawYDistance;
        }

        if (rawZDistance < 0 ) {
            zDistance = opposite(rawZDistance);
        } else {
            zDistance = rawZDistance;
        }

        return MathHelper.sqrt((float) (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance));
    }

    /** Makes number opposite. For example:
    double a = 1;
    double oppositeA = this.opposite(a);

    And "oppositeA" will be equal to -1.
    */
    private double opposite(double numberToOpposite) {
        double oppositeNumber;

        if (numberToOpposite > 0 || numberToOpposite < 0) {
            oppositeNumber = numberToOpposite - numberToOpposite * 2;
        } else {
            oppositeNumber = 0;
        }

        return oppositeNumber;
    }
}
