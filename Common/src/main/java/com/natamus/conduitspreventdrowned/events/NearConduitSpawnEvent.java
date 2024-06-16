package com.natamus.conduitspreventdrowned.events;

import com.natamus.conduitspreventdrowned.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class NearConduitSpawnEvent {
	public static boolean onEntitySpawn(Mob entity, ServerLevel world, BlockPos spawnerPos, MobSpawnType spawnReason) {
		if (!(entity instanceof Drowned) && !(entity instanceof Guardian)) {
			return true;
		}
		
		BlockPos ePos = entity.blockPosition();
		int r = ConfigHandler.preventDrownedInRange;
		
		for (Player player : world.players()) {
			BlockPos playerPos = BlockPos.containing(player.getX(), 1, player.getZ());
			if (playerPos.closerThan(new BlockPos(ePos.getX(), 1, ePos.getZ()), r)) {
				Collection<MobEffectInstance> activeEffects = player.getActiveEffects();
				if (!activeEffects.isEmpty()) {
					boolean foundConduit = false;
					for (MobEffectInstance pe : activeEffects) {
						if (pe.getEffect().equals(MobEffects.CONDUIT_POWER)) {
							foundConduit = true;
							break;
						}
					}
					if (foundConduit) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
