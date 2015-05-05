package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.filter.FilterParser;

import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@ModuleInfo(name="filters", module = FilterModule.class)
public class FilterModule extends Module {

	private FilterParser fp;

	public FilterModule() {

	}

	public FilterModule(FilterParser fp) {
		this.fp = fp;
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		return new FilterModule(new FilterParser(root, "filters"));
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public FilterParser getFilterParser() {
		return this.fp;
	}
}
