package me.skylertyler.scrimmage.modules;

import java.util.HashMap;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionParser;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ModuleInfo(name = "regions", desc = "a region module", module = RegionModule.class)
public class RegionModule extends Module {

	public HashMap<String, Region> regions;

	private RegionParser rp;

	public RegionModule() {
		// nothing
	}

	public RegionModule(RegionParser rp) {
		this.rp = rp;
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		return new RegionModule(new RegionParser(root, "regions")) != null ? new RegionModule(
				new RegionParser(root, "regions")) : null;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public RegionParser getRegionParser() {
		return this.rp;
	}
}
