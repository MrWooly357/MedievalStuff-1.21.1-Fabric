package net.mrwooly357.medievalstuff.entity.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.mrwooly357.medievalstuff.entity.damage.ModDamageTypes;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;
import net.mrwooly357.medievalstuff.util.ModTags;

public class SoulDecayStatusEffect extends StatusEffect {

    public SoulDecayStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public SoulDecayStatusEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }


    @Override
    public boolean applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.hasStatusEffect(ModStatusEffects.SOUL_PROTECTION)) {
            livingEntity.damage(livingEntity.getDamageSources().create(ModDamageTypes.SOUL_DECAY), 1.0F +  (livingEntity.getMaxHealth() - livingEntity.getHealth()) * 0.2F);

            if (livingEntity instanceof PlayerEntity playerEntity) {
                playerEntity.getHungerManager().addExhaustion(0.15F * (float) (amplifier + 1));
            }
        }

        return livingEntity.getType().isIn(ModTags.EntityTypes.SOULFUL) && !livingEntity.getType().isIn(ModTags.EntityTypes.SOULLESS);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 200 >> amplifier;

        return i == 0 || duration % i == 0;
    }
}
