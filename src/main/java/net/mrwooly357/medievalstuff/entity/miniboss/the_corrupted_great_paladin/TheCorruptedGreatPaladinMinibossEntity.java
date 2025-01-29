package net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class TheCorruptedGreatPaladinMinibossEntity extends HostileEntity implements RangedAttackMob {


    public TheCorruptedGreatPaladinMinibossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
    }
}
