package com.ethamorim.conduitpower.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeSpawnEvent {
	@SubscribeEvent
	public void onEntitySpawn(MobSpawnEvent.FinalizeSpawn e) {
		LevelAccessor level = e.getLevel();
		if (!(level instanceof Level)) return;
		if (level.isClientSide()) return;

		Mob entity = e.getEntity();
		if (entity == null) {
			return;
		}
		if (!NearConduitSpawnEvent.onEntitySpawn(entity, (ServerLevel) level, null, e.getSpawnType())) {
			e.setSpawnCancelled(true);
			e.setCanceled(true);
		}
	}
}
