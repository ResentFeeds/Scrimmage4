package me.skylertyler.scrimmage.modules;

import java.util.logging.Level;

import me.skylertyler.scrimmage.map.MapAuthor;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.map.MapVersion;
import me.skylertyler.scrimmage.utils.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//TODO fix this and change the mapLoader! stuff!
@ModuleDescription(name = "info", description = "basic information about the current map!")
public class InfoModule extends Module {

	protected String name;
	protected String objective;
	protected MapAuthor authors;

	protected MapVersion version;

	@Override
	public void unload() {

	}

	@Override
	public void load(Document doc) {
		Node nameElement = doc.getElementsByTagName("name").item(0);
		if (nameElement == null) {
			Log.logWarning("there is no name element in the xml file");
		}
		this.name = nameElement.getTextContent();
		Node versionElement = doc.getElementsByTagName("version").item(0);

		Node objectiveElement = doc.getElementsByTagName("objective").item(0);
		if (objectiveElement == null) {
			Log.logWarning("there is no objective element in the xml file");
		}
		this.objective = objectiveElement.getTextContent();

		if (versionElement == null) {
			Log.logWarning("there is no version element in the xml file");
		}

		MapVersion current = MapVersion.parseVersion(versionElement
				.getTextContent());

		this.version = current;

		Node node = doc.getElementsByTagName("authors").item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE
				&& node.getNodeName().equals("author")) {
			Element e = (Element) node;
			NodeList authors = e.getChildNodes();
			this.authors = new MapAuthor();
			for (int i = 0; i < authors.getLength(); i++) {
				Node newAuthor = authors.item(i);
				this.authors.addAuthor(newAuthor.getTextContent());

				for (String author : this.authors.getAuthors()) {
					Log.logMessage(Level.INFO, author);
				}
			}
		}
		try {
			// load contributors
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public MapInfo loadInfo() {
		MapInfo info = new MapInfo(name, version, authors, null, objective,
				null);
		return info;
	}
}
