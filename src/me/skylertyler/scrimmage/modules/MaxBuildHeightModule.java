package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.maxheight.MaxHeightParser;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;

@ModuleInfo(name = "maxheight", module = MaxBuildHeightModule.class)
public class MaxBuildHeightModule extends Module {

	private final MaxHeightParser mhp;

	public MaxBuildHeightModule() {
		this.mhp = null;
	}

	public MaxBuildHeightModule(MaxHeightParser mhp) {
		this.mhp = mhp;
	}

	@Override
	public Module parse(Document doc) {
		return new MaxBuildHeightModule(new MaxHeightParser(
				doc.getDocumentElement(), "maxbuildheight"));
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public MaxHeightParser getMaxHeightParser() {
		return this.mhp;
	}
}
