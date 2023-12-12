package com.natamus.conduitspreventdrowned.events;

import com.natamus.conduitspreventdrowned.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class DrownedEvent {
	public static boolean onDrownedSpawn(Mob entity, ServerLevel world, BlockPos spawnerPos, MobSpawnType spawnReason) {
		if (!(entity instanceof Drowned)) {
			return true;
		}
		
		BlockPos epos = entity.blockPosition();
		int r = ConfigHandler.preventDrownedInRange;
		
		for (Player player : world.players()) {
			BlockPos playerpos = BlockPos.containing(player.getX(), 1, player.getZ());
			if (playerpos.closerThan(new BlockPos(epos.getX(), 1, epos.getZ()), r)) {
				Collection<MobEffectInstance> activeeffects = player.getActiveEffects();
				if (activeeffects.size() > 0) {
					boolean foundconduit = false;
					for (MobEffectInstance pe : activeeffects) {
						if (pe.getEffect().equals(MobEffects.CONDUIT_POWER)) {
							foundconduit = true;
							break;
						}
					}
					if (foundconduit) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
