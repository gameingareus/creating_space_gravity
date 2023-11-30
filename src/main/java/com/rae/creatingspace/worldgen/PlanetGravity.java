package com.rae.creatingspace.worldgen;


import com.rae.creatingspace.server.blockentities.GravityGeneratorBlockEntity;
import com.rae.creatingspace.utilities.CSDimensionUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;
import java.util.Collections;

public class PlanetGravity {


    private double getGravityFactor(LivingEntity entity) {
        ResourceKey<DimensionType> dimensionTypeKey = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, entity.level.dimension().location());
        float gravityValue = CSDimensionUtil.gravity(dimensionTypeKey);
        double gravityFactor = (double) gravityValue / 9.81;
        return Math.round(gravityFactor * 1000.0) / 1000.0;
    }

    @SubscribeEvent
    public void onLivingUpdate(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            LivingEntity entity = event.player;

            double gravityFactor = getGravityFactor(entity);

            if (!((Player) entity).isCreative() && !entity.isOnGround() && !isPlayerWithinAnyGravityGeneratorRange(entity)) {
                Vec3 motion = entity.getDeltaMovement();
                entity.setDeltaMovement(motion.x, motion.y * gravityFactor, motion.z);

                int jumpBoostStrength = calculateJumpBoostStrength(gravityFactor);
                applyJumpBoost(entity, jumpBoostStrength);
            } else {
                if (gravityFactor == 1.00) {
                    removeJumpBoost(entity);
                }
            }
        }
    }

    // Calculate jump boost strength based on gravity factor
    private int calculateJumpBoostStrength(double gravityFactor) {
        return (int) Math.round((1.0 - gravityFactor) * 10);
    }

    // Apply jump boost potion effect with infinite duration
    private void applyJumpBoost(LivingEntity entity, int strength) {
        MobEffectInstance jumpBoostEffect = new MobEffectInstance(MobEffects.JUMP, Integer.MAX_VALUE, strength - 1, false, false);
        entity.addEffect(jumpBoostEffect);
    }
    private void removeJumpBoost(LivingEntity entity) {
        entity.removeEffect(MobEffects.JUMP);
    }

    private boolean isPlayerWithinAnyGravityGeneratorRange(LivingEntity entity) {
        // Iterate through all GravityGeneratorBlockEntities in the world
        // and check if the player is within range
        for (GravityGeneratorBlockEntity generator : getAllGravityGenerators()) {
            if (generator.isEntityWithinRange(entity)) {
                return true;
            }
        }
        return false;
    }

    private Collection<GravityGeneratorBlockEntity> getAllGravityGenerators() {
        return Collections.emptyList();
    }
}
