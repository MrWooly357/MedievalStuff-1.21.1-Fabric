package net.mrwooly357.medievalstuff.entity.mob.passive.jelly;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import org.jetbrains.annotations.Nullable;

public class JellyEntity extends AnimalEntity {
    public final AnimationState basicJellyAnimationState = new AnimationState();
    private int basicJellyAnimationTimeout = 0;


    public JellyEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.4));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.2));
        this.goalSelector.add(3, new TemptGoal(this, 0.1, stack -> stack.isOf(Items.WARPED_FUNGUS), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.2));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 4F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    private void setupAnimationStates() {
        if(this.basicJellyAnimationTimeout <= 0) {
            this.basicJellyAnimationTimeout = 20;
            basicJellyAnimationState.start(this.age);
        } else {
            --this.basicJellyAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public static DefaultAttributeContainer.Builder createJellyAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.WARPED_FUNGUS);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntityTypes.JELLY.create(world);
    }

    /* SOUNDS */

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH_SMALL;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL;
    }
}
