package me.skylertyler.scrimmage.modules;

import java.util.HashMap;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;

import org.bukkit.event.HandlerList; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ModuleInfo(name = "regions", desc = "a region module", module = RegionModule.class)
public class RegionModule extends Module {

	public HashMap<String, Region> regions;

	public RegionModule() {
		this.regions = null;
	}

	public RegionModule(HashMap<String, Region> regions) {
		this.regions = regions;
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		HashMap<String, Region> regions = RegionUtils.parseRegions(root, "regions");
		return new RegionModule(regions);
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	// dont use this getter use this in the RegionUtils getRegions method instead!
	public HashMap<String, Region> getRegions(){
		return this.regions;
	} 
}
