// Source code is decompiled from a .class file using FernFlower decompiler.
package com.dmcclean780.myfirstmod.entity.custom;

import com.dmcclean780.myfirstmod.entity.ai.BasiliskShootFireGoal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class BasiliskEntity extends Monster {
    public boolean isChickenJockey;
    public boolean isFlapping;
    public float flapAmount = 1;
    public float currentWingRotation = 0;
    private boolean wingFalling = false;
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(BasiliskEntity.class,
            EntityDataSerializers.BOOLEAN);

    public BasiliskEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new BasiliskShootFireGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Cow.class, true));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        return super.getDefaultDimensions(pose);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 8; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.onGround()) {
            setIsFlapping(true);
            if (this.currentWingRotation < this.flapAmount && !this.wingFalling) {
                this.currentWingRotation += 0.5;
            } else if (this.currentWingRotation >= 0.5) {
                this.wingFalling = true;
                this.currentWingRotation -= 0.5;
            } else if (this.currentWingRotation == 0) {
                this.wingFalling = false;
            }
        } else {
            this.currentWingRotation = 0;
            setIsFlapping(false);
        }

        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0) {
            this.setDeltaMovement(vec3.multiply(1.0, 0.6, 1.0));
        }
    }

    @Override
    protected int calculateFallDamage(float fallDistance, float damageMultiplier) {
        return 0;
    }

    public boolean checkIsFlapping() {
        return this.isFlapping;
    }

    public void setIsFlapping(boolean isFlapping) {
        this.isFlapping = isFlapping;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    protected int getBaseExperienceReward() {
        return this.isChickenJockey() ? 10 : super.getBaseExperienceReward();
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.isChickenJockey = compound.getBoolean("IsChickenJockey");
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsChickenJockey", this.isChickenJockey);
    }

    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return this.isChickenJockey();
    }

    protected void positionRider(Entity passenger, Entity.MoveFunction callback) {
        super.positionRider(passenger, callback);
        if (passenger instanceof LivingEntity) {
            ((LivingEntity) passenger).yBodyRot = this.yBodyRot;
        }

    }

    public boolean isChickenJockey() {
        return this.isChickenJockey;
    }

    public void setChickenJockey(boolean isChickenJockey) {
        this.isChickenJockey = isChickenJockey;
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
        builder.define(DATA_FLAGS_ID, (byte) 0);
    }

    private boolean isCharged() {
        return ((Byte) this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setCharged(boolean charged) {
        byte b0 = (Byte) this.entityData.get(DATA_FLAGS_ID);
        if (charged) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(BasiliskEntity.class, EntityDataSerializers.BYTE);
    }
}
