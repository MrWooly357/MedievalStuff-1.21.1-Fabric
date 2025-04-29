package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.mrwooly357.medievalstuff.util.ModUtil;

public class TheGreatPaladinsClaymoreItem extends AdvancedSweepMeleeWeaponItem {
    public TheGreatPaladinsClaymoreItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        if (ModUtil.getDistanceBetween(target.getX(), target.getY(), target.getZ(), attacker.getX(), attacker.getY(), attacker.getZ()) > 3.25F) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 0, false, true), attacker);
            target.damage(target.getDamageSources().generic(), 1F);
        }
    }
}
