package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.map.MapVersion;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

//TODO fix this and change the mapLoader! stuff!
@ModuleDescription(name = "info", description = "basic information about the current map!")
public class InfoModule extends Module {

	protected String name;

	protected MapVersion version;

	@Override
	public void unload() {

	}

	@Override
	public void load(Document doc) {
		Node nameElement = doc.getElementsByTagName("name").item(0);
		this.name = nameElement.getTextContent();

		Node versionElement = doc.getElementsByTagName("version").item(0);
		MapVersion current = MapVersion.parseVersion(versionElement
				.getTextContent());
		this.version = current;

	}
}
