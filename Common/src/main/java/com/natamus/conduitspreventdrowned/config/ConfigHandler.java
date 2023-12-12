package com.natamus.conduitspreventdrowned.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.conduitspreventdrowned.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 400) public static int preventDrownedInRange = 400;

	public static void initConfig() {
		configMetaData.put("preventDrownedInRange", Arrays.asList(
			"The euclidian distance range around the drowned where a check for a player with the conduit effect is done. A value of 400 prevents the spawning of all drowned around."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}