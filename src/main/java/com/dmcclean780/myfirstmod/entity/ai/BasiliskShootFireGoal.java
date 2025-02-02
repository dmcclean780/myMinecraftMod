package com.dmcclean780.myfirstmod.entity.ai;

import java.util.EnumSet;
import net.minecraft.world.entity.ai.attributes.Attributes;

import com.dmcclean780.myfirstmod.entity.custom.BasiliskEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;

public class BasiliskShootFireGoal extends Goal {
    private final BasiliskEntity basilisk;
    private int attackStep;
    private int attackTime;
    private int lastSeen;

    public BasiliskShootFireGoal(BasiliskEntity basilisk) {
        this.basilisk = basilisk;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.basilisk.getTarget();
        return livingentity != null && livingentity.isAlive() && this.basilisk.canAttack(livingentity);
    }

    public void start() {
        this.attackStep = 0;
    }

    public void stop() {
        this.basilisk.setCharged(false);
        this.lastSeen = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.attackTime;
        LivingEntity livingentity = this.basilisk.getTarget();
        if (livingentity != null) {
            boolean flag = this.basilisk.getSensing().hasLineOfSight(livingentity);
            if (flag) {
                this.lastSeen = 0;
            } else {
                ++this.lastSeen;
            }

            double d0 = this.basilisk.distanceToSqr(livingentity);
            if (d0 < 4.0) {
                if (!flag) {
                    return;
                }

                this.basilisk.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(),
                        livingentity.getZ(), 1.0);
            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                double d1 = livingentity.getX() - this.basilisk.getX();
                double d2 = livingentity.getY(0.5) - this.basilisk.getY(0.5);
                double d3 = livingentity.getZ() - this.basilisk.getZ();
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.basilisk.setCharged(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                        this.basilisk.setCharged(false);
                    }

                    if (this.attackStep > 1) {
                        double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5;
                        if (!this.basilisk.isSilent()) {
                            this.basilisk.level().levelEvent((Player) null, 1018, this.basilisk.blockPosition(), 0);
                        }

                        for (int i = 0; i < 1; ++i) {
                            Vec3 vec3 = new Vec3(this.basilisk.getRandom().triangle(d1, 2.297 * d4), d2,
                                    this.basilisk.getRandom().triangle(d3, 2.297 * d4));
                            SmallFireball smallfireball = new SmallFireball(this.basilisk.level(), this.basilisk,
                                    vec3.normalize());
                            smallfireball.setPos(smallfireball.getX(), this.basilisk.getY(0.5) + 0.1,
                                    smallfireball.getZ() + 0.5);
                            this.basilisk.level().addFreshEntity(smallfireball);
                        }
                    }
                }

                this.basilisk.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
            } else if (this.lastSeen < 5) {
                this.basilisk.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(),
                        livingentity.getZ(), 1.0);
            }

            super.tick();
        }

    }

    private double getFollowDistance() {
        return this.basilisk.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}
