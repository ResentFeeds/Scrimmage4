package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.kit.KitParser;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//FIx kits 
@ModuleInfo(name = "kits", module = KitModule.class, requires = TeamModule.class)
public class KitModule extends Module { 
	
	private final KitParser parser;

	public KitModule() {
		this.parser = null;
	}

	public KitModule(KitParser parser) {
		this.parser = parser;
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		return new KitModule(new KitParser(root, "kits", "kit"));
	}

	public KitParser getKitParser() {
		return this.parser;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

}
