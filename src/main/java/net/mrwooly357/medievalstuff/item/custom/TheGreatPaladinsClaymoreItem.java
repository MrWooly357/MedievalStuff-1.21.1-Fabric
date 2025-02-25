package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.mrwooly357.wool_lib.items.weapons.AdvancedSweepMeleeWeaponItem;
import net.mrwooly357.wool_lib.maths.ModMathHelper;

public class TheGreatPaladinsClaymoreItem extends AdvancedSweepMeleeWeaponItem {
    public TheGreatPaladinsClaymoreItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        if (ModMathHelper.getDistanceBetweenEntities(target, attacker) > 3.25F) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 0, false, true), attacker);
            target.damage(target.getDamageSources().generic(), 1F);
        }
    }
}
