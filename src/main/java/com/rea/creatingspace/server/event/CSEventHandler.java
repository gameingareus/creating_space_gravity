package com.rea.creatingspace.server.event;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.init.DamageSourceInit;
import com.rea.creatingspace.init.worldgen.DimensionInit;
import com.rea.creatingspace.utilities.CustomTeleporter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreatingSpace.MODID)
public class CSEventHandler {

    public CSEventHandler() {

    }
    @SubscribeEvent
    public static void entityLivingEvent(LivingEvent.LivingTickEvent livingTickEvent){
        final LivingEntity entityLiving = livingTickEvent.getEntity();
        Level level = entityLiving.getLevel();
        ResourceKey<Level> dimension = level.dimension();
        if (entityLiving instanceof ServerPlayer player){
            if (DimensionInit.gravity(level.dimensionTypeId())==0){
                if (!level.isClientSide){
                    if (player.getY() < level.dimensionType().minY()+10){
                        ResourceKey<Level> dimensionToTeleport = null;
                        if (dimension == DimensionInit.EARTH_ORBIT_KEY){
                            dimensionToTeleport = Level.OVERWORLD;
                        }
                        if (dimension == DimensionInit.MOON_ORBIT_KEY){
                            dimensionToTeleport = DimensionInit.MOON_KEY;
                        }
                        if (dimensionToTeleport!=null){
                            ServerLevel destServerLevel = level.getServer().getLevel(dimensionToTeleport);

                            player.changeDimension(destServerLevel, new CustomTeleporter(destServerLevel));
                        }
                    }
                }
            }
        }
        /*
        if (entityLiving.tickCount % 20 == 0) {
            if (!(dimension == Level.OVERWORLD)&&(entityLiving.isAttackable())) {
                entityLiving.hurt(DamageSourceInit.NO_OXYGEN, 0.5f);
            }
        }*/
    }
}
