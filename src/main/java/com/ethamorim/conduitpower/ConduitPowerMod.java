package com.ethamorim.conduitpower;

import com.ethamorim.conduitpower.event.ForgeSpawnEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ConduitPowerMod.MOD_ID)
public class ConduitPowerMod {

	public static final String MOD_ID = "conduitpower";
	
	public ConduitPowerMod() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::loadComplete);

		setGlobalConstants();
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
    	MinecraftForge.EVENT_BUS.register(new ForgeSpawnEvent());
	}

	private static void setGlobalConstants() {

	}
}