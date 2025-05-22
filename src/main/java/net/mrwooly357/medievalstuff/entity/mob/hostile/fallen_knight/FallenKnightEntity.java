package net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import net.mrwooly357.medievalstuff.entity.mob.GhostEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class FallenKnightEntity extends HostileEntity implements GhostEntity {
    private static Predicate<Difficulty> DOOR_BREAK_DIFFICULTY_CHECKER = (difficulty) -> difficulty == Difficulty.HARD;
    /*/public static final MemoryModuleType<FallenKnightEntity> NEAREST_GROUP_MEMBER =
            MemoryModuleType.register("NEAREST_GROUP_MEMBER", EntityPredicates.canBeLeashedTo(null), FallenKnightEntity.class);/*/
    public FallenKnightEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }


    public static DefaultAttributeContainer createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20F)
                .add(EntityAttributes.GENERIC_ARMOR, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.5F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
                .build();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        //this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, (double)1.25F));
        //this.goalSelector.add(1, new AttackGoal(this, (double)1.25F)));
        this.goalSelector.add(0, new MeleeAttackGoal(this, 1.1D, true));
        //this.goalSelector.add(0, new BreakDoorGoal());
        this.goalSelector.add(0, new WanderAroundFarGoal(this, 1F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(16));
        //setCanBreakDoors(true);
        this.goalSelector.add(2, new BreakDoorGoal(this, DOOR_BREAK_DIFFICULTY_CHECKER));
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    public float sunlightProtection() {
        return 1.0F;
    }

    @Override
    public boolean isShaking() {
        return isShaking(this);
    }
    static {
        FabricDefaultAttributeRegistry.register(ModEntityTypes.FALLEN_KNIGHT, MobEntity.createMobAttributes().build());
    }
}
