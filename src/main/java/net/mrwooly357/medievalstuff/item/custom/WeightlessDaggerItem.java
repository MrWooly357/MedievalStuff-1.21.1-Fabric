package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class WeightlessDaggerItem extends AdvancedSweepMeleeWeaponItem {
    private final RegistryEntry<StatusEffect> effect;

    public WeightlessDaggerItem(ToolMaterial toolMaterial, Settings settings, RegistryEntry<StatusEffect> effect) {
        super(toolMaterial, settings);
        this.effect = effect;
    }

    int counter = 0;
    int x = 0;
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(x == 0) {
            if(counter == 2) {
                int chanceToAddLevitation3 = MathHelper.nextInt(Random.createLocal(), 1, 2);
                if (chanceToAddLevitation3 == 1) {
                    target.addStatusEffect(new StatusEffectInstance(effect, 95, 0), attacker);
                    x = 1;
                }
            }
        }
        if(x == 0) {
            if(counter == 1) {
                int chanceToAddLevitation2 = MathHelper.nextInt(Random.createLocal(), 1, 3);
                if (chanceToAddLevitation2 == 1) {
                    target.addStatusEffect(new StatusEffectInstance(effect, 85, 0), attacker);
                    x = 1;
                } else {
                    counter = 2;
                }
            }
        }
        if(x == 0) {
            if(counter == 0) {
                int chanceToAddLevitation1 = MathHelper.nextInt(Random.createLocal(), 1, 4);
                if (chanceToAddLevitation1 == 1) {
                    target.addStatusEffect(new StatusEffectInstance(effect, 75, 0), attacker);
                } else {
                    counter = 1;
                }
            }
        }
        if(x == 1) {
            counter = 0;
            x = 0;
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.medievalstuff.weightless_dagger_1.tooltip"));
        if(Screen.hasControlDown()) {
            tooltip.add(Text.translatable("tooltip.medievalstuff.weightless_dagger_2.tooltip"));
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.weightless_dagger_4.tooltip"));
        }
        if(Screen.hasAltDown()) {
            tooltip.add(Text.translatable("tooltip.medievalstuff.weightless_dagger_3.tooltip"));
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.weightless_dagger_5.tooltip"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
