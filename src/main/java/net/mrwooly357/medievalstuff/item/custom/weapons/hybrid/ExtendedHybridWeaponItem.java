package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.text.DecimalFormat;
import java.util.List;

public abstract class ExtendedHybridWeaponItem extends Item implements ProjectileItem {

    protected static final Identifier BASE_ATTACK_KNOCKBACK_MODIFIER_ID = Identifier.ofVanilla("base_attack_knockback_");
    protected static final Identifier BASE_PLAYER_BLOCK_INTERACTION_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_player_block_interaction_range");
    protected static final Identifier BASE_PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_player_entity_interaction_range");

    HybridWeaponMaterial hybridWeaponMaterial;
    HybridWeaponFamily hybridWeaponFamily;
    HybridWeaponClass hybridWeaponClass;

    public ExtendedHybridWeaponItem(Item.Settings settings, HybridWeaponMaterial hybridWeaponMaterial, HybridWeaponClass hybridWeaponClass) {
        super(settings
                .maxDamage(
                hybridWeaponMaterial.getDurability()
                + hybridWeaponClass.getHybridWeaponfamily().getDurability()
                + hybridWeaponClass.getDurability()
                )
                .maxCount(1)
                .component(DataComponentTypes.TOOL, createToolComponent())
        );
        this.hybridWeaponMaterial = hybridWeaponMaterial;
        hybridWeaponFamily = hybridWeaponClass.getHybridWeaponfamily();
        this.hybridWeaponClass = hybridWeaponClass;
    }


    public static AttributeModifiersComponent createAttributeModifiers(HybridWeaponMaterial hybridWeaponMaterial, HybridWeaponClass hybridWeaponClass) {
        HybridWeaponFamily hybridWeaponFamily = hybridWeaponClass.getHybridWeaponfamily();
        double GENERIC_ATTACK_DAMAGE = hybridWeaponMaterial.getAttackDamage() + hybridWeaponFamily.getAttackDamage() + hybridWeaponClass.getAttackDamage();
        double GENERIC_ATTACK_SPEED = hybridWeaponMaterial.getAttackSpeed() + hybridWeaponFamily.getAttackSpeed() + hybridWeaponClass.getAttackSpeed();
        double GENERIC_ATTACK_KNOCKBACK = hybridWeaponMaterial.getAdditionalAttackKnockback() + hybridWeaponFamily.getAdditionalAttackKnockback() + hybridWeaponClass.getAdditionalAttackKnockback();
        double PLAYER_INTERACTION_RANGE = hybridWeaponMaterial.getAdditionalAttackRange() + hybridWeaponFamily.getAdditionalAttackRange() + hybridWeaponClass.getAdditionalAttackRange();

        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, GENERIC_ATTACK_DAMAGE - 1, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID, GENERIC_ATTACK_SPEED - 4, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(
                                BASE_ATTACK_KNOCKBACK_MODIFIER_ID, GENERIC_ATTACK_KNOCKBACK, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                BASE_PLAYER_BLOCK_INTERACTION_RANGE_MODIFIER_ID, PLAYER_INTERACTION_RANGE, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                BASE_PLAYER_ENTITY_INTERACTION_RANGE_MODIFIER_ID, PLAYER_INTERACTION_RANGE, EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build().withShowInTooltip(false);
    }

    protected static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    public boolean hasSweepingAttack() {
        return false;
    }

    @Override
    public int getEnchantability() {
        return hybridWeaponMaterial.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return hybridWeaponMaterial.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isInCreativeMode();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (hasDescription()) {

            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable(getDescriptionTranslationKey()));
            } else {
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.keyboard_shift"));
            }
        }

        if (Screen.hasControlDown()) {
            int durability = getDurability() - stack.getDamage();
            DecimalFormat decimalFormat = new DecimalFormat("#.###");

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.attack_damage")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getAttackDamage()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.attack_speed")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getAttackSpeed()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.attack_critical_chance")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getAttackCriticalChance()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.additional_attack_knockback")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getAdditionalAttackKnockback()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.additional_attack_range")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getAdditionalAttackRange()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.charge_time")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(String.valueOf(getChargeTime()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.throw_power")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getThrowPower()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.throw_accuracy")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getThrowAccuracy()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.throw_critical_chance")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getThrowCriticalChance()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.projectile_gravity")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(decimalFormat.format(getProjectileGravity()))
                            .formatted(Formatting.DARK_GREEN)));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.durability")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.literal(durability + "/" + getDurability())
                            .formatted(Formatting.DARK_GREEN)));
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.keyboard_control"));
        }

        if (Screen.hasAltDown()) {

            if (Screen.hasControlDown()) {
                tooltip.add(Text.literal("").formatted(Formatting.GRAY));
            }

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.material")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.translatable(hybridWeaponMaterial.getTranslationKey())));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.family")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.translatable(hybridWeaponFamily.getTranslationKey())));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.class")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.translatable(hybridWeaponClass.getTranslationKey())));

            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.type")
                    .append(Text.literal(": ")
                            .formatted(Formatting.GRAY))
                    .append(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.type_hybrid")));
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_hybrid_weapon_item.keyboard_alt"));
        }
    }

    public float getAttackDamage() {
        return hybridWeaponMaterial.getAttackDamage() + hybridWeaponFamily.getAttackDamage() + hybridWeaponClass.getAttackDamage();
    }

    public float getAttackSpeed() {
        return hybridWeaponMaterial.getAttackSpeed() + hybridWeaponFamily.getAttackSpeed() + hybridWeaponClass.getAttackSpeed();
    }

    public float getAttackCriticalChance() {
        return hybridWeaponMaterial.getAttackCriticalChance() + hybridWeaponFamily.getAttackCriticalChance() + hybridWeaponClass.getAttackCriticalChance();
    }

    public float getAdditionalAttackKnockback() {
        return hybridWeaponMaterial.getAdditionalAttackKnockback() + hybridWeaponFamily.getAdditionalAttackKnockback() + hybridWeaponClass.getAdditionalAttackKnockback();
    }

    public float getAdditionalAttackRange() {
        return hybridWeaponMaterial.getAdditionalAttackRange() + hybridWeaponFamily.getAdditionalAttackRange() + hybridWeaponClass.getAdditionalAttackRange();
    }

    public int getChargeTime() {
        return hybridWeaponMaterial.getChargeTime() + hybridWeaponFamily.getChargeTime() + hybridWeaponClass.getChargeTime();
    }

    public float getThrowPower() {
        return hybridWeaponMaterial.getThrowPower() + hybridWeaponFamily.getThrowPower() + hybridWeaponClass.getThrowPower();
    }

    public float getThrowAccuracy() {
        return hybridWeaponMaterial.getThrowAccuracy() + hybridWeaponFamily.getThrowAccuracy() + hybridWeaponClass.getThrowAccuracy();
    }

    public float getThrowCriticalChance() {
        return hybridWeaponMaterial.getThrowCriticalChance() + hybridWeaponFamily.getThrowCriticalChance() + hybridWeaponClass.getThrowCriticalChance();
    }

    public float getProjectileGravity() {
        return hybridWeaponMaterial.getProjectileGravity() + hybridWeaponFamily.getProjectileGravity() + hybridWeaponClass.getProjectileGravity();
    }

    public int getDurability() {
        return hybridWeaponMaterial.getDurability() + hybridWeaponFamily.getDurability() + hybridWeaponClass.getDurability();
    }

    protected boolean hasDescription() {
        return false;
    }

    protected String setTranslationKeyName() {
        return "";
    }

    protected String getDescriptionTranslationKey() {

        return "tooltip.medievalstuff.extended_hybrid_weapon_item.description." + setTranslationKeyName();
    }

    public float getBonusAttackDamage(PlayerEntity attacker, Entity target) {
        return 0.0F;
    }

    public int throwDurabilityRequirement() {
        return 2;
    }
}
