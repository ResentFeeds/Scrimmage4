package me.skylertyler.scrimmage.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.exception.MapInfoLoadException;
import me.skylertyler.scrimmage.utils.BukkitUtils;
import me.skylertyler.scrimmage.utils.ConsoleUtils;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.MapDocument;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MapLoader {

	protected List<Map> loadedMaps = null;
	protected Document doc;
	protected Element root;

	public MapLoader() {
		this.loadedMaps = new ArrayList<Map>();
	}

	public void loadMaps() throws IOException, MapInfoLoadException,
			SAXException, ParserConfigurationException {
		if (Scrimmage.getScrimmageInstance().hasRotationFile()) {
			for (File file : Scrimmage.getScrimmageInstance().getRotationFile()
					.listFiles()) {
				File xml = new File(file, "map.xml");
				if (isLoadable(file, xml)) {
					this.doc = MapDocument.getXMLDocument(xml);
					this.root = (Element) this.doc.getElementsByTagName("map")
							.item(0);
					MapInfo info = this.loadMapInfo(getRootElement());
					Map map = new Map(xml, info);
					loadedMaps.add(map);
				}
			}
		}
	}

	private MapInfo loadMapInfo(Element root) throws MapInfoLoadException {
		if (root.getNodeType() == Node.ELEMENT_NODE) {
			if (root != null) {
				String name = root.getElementsByTagName("name").item(0)
						.getTextContent();
				String objective = root.getElementsByTagName("objective")
						.item(0).getTextContent();
				String version = root.getElementsByTagName("version").item(0)
						.getTextContent();
				if (!(name.isEmpty() && objective.isEmpty() && version
						.isEmpty())) {
					return new MapInfo(name, MapVersion.parseVersion(version),
							null, loadContributors(root), objective,
							loadRules(root));

				} else {
					Log.logInfo("there is no name for the map.xml ");
				}
			}
		}
		return null;
	}

	private MapAuthor loadAuthors(Element root) {
		MapAuthor authors = null;
		Node n = root.getElementsByTagName("authors").item(0);
		if (n != null) {
			Element e = (Element) n;
			if (e.getNodeType() == Node.ELEMENT_NODE
					&& e.getNodeName().equals("author")) {
			}
		}
		return authors;
	}

	private MapContributor loadContributors(Element root) {
		Node contributors = root.getElementsByTagName("contributors").item(0);
		if (contributors.getNodeType() == Node.ELEMENT_NODE) {
			Element contributorsElement = (Element) contributors;
			NodeList contributorList = contributorsElement.getChildNodes();
			for (int i = 0; i < contributorList.getLength(); i++) {
				Node node = contributorList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE
						&& node.getNodeName().equals("contributor")) {
					Element element = (Element) node;
					MapContributor contributor = new MapContributor();
					contributor.addContributor(
							element.getAttribute("contribution"),
							element.getTextContent());
					for (Entry<String, String> value : contributor
							.getContributors().entrySet()) {
						ConsoleUtils.sendConsoleMessage(value.getKey()
								+ " is the key, " + value.getValue()
								+ " is the value!");
					}
					return contributor;
				}
			}
		}
		return null;
	}

	private MapRules loadRules(Element root) {
		MapRules mapRule = new MapRules();
		Node rules = root.getElementsByTagName("rules").item(0);
		if (rules != null) {
			if (rules.getNodeType() == Node.ELEMENT_NODE) {
				Element rulesElement = (Element) rules;
				NodeList list = rulesElement.getChildNodes();
				for (int c = 0; c < list.getLength(); c++) {
					Node nc = list.item(c);
					if (nc.getNodeType() == Node.ELEMENT_NODE
							&& nc.getNodeName().equals("rule")) {
						Element e = (Element) nc;
						String rule = BukkitUtils.colorize(e.getTextContent());
						mapRule.rules.add(rule);
						return mapRule;
					}
				}
			}
		}
		return null;
	}

	public boolean isLoadable(File file, File xml) {
		if (new File(file, "region").exists() && xml.isFile() && xml.exists()
				&& new File(file, "level.dat").exists()) {
			return true;
		}
		return false;
	}

	public List<Map> getLoadedMaps() {
		return this.loadedMaps;
	}

	public Map getMap(String name) {
		Map map = null;
		for (Map maps : this.getLoadedMaps()) {
			if (maps.getInfo().getName().equals(name)) {
				map = maps;
			}
		}
		return map;
	}

	public Document getXMLDocument() {
		return this.doc;
	}

	public Element getRootElement() {
		return this.root;
	}
}
