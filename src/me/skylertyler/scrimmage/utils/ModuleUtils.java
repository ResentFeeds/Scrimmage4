package me.skylertyler.scrimmage.utils;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule;
import me.skylertyler.scrimmage.modules.ModuleContainer;
import me.skylertyler.scrimmage.modules.RegionModule;

public class ModuleUtils {

	static ModuleContainer container = Scrimmage.getScrimmageInstance().getLoader()
			.getContainer();

	public static RegionModule getRegionModule() {
		return (RegionModule) getModuleContainer().getModule("regions");
	}

	public static boolean hasRegionModule() {
		return getRegionModule() != null;
	}
	
	public static MaxBuildHeightModule getMaxBuildHeightModule(){
		return (MaxBuildHeightModule) getModuleContainer().getModule("maxheight");
	}
	
	public static ModuleContainer getModuleContainer(){
		return container;
	}
}
