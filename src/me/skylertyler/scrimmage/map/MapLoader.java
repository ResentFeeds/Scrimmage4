package me.skylertyler.scrimmage.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.modules.ModuleContainer;
import me.skylertyler.scrimmage.utils.MapDocument;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class MapLoader {

	protected List<Map> loadedMaps = null;

	protected Document doc;

	protected Element root;
	protected Scrimmage scrim;

	protected ModuleContainer container;

	protected Map nextMap = null;

	public MapLoader(Scrimmage scrim) {
		this.scrim = scrim;
		this.loadedMaps = new ArrayList<Map>();
	}

	public void loadMaps() throws SAXException, IOException,
			ParserConfigurationException {
		boolean hasRotation = Scrimmage.getScrimmageInstance()
				.hasRotationFile();
		if (hasRotation) {
			File rotationFile = Scrimmage.getScrimmageInstance()
					.getRotationFile();
			for (File file : rotationFile.listFiles()) {
				File xml = new File(file, "map.xml");
				if (isLoadable(file, xml)) {
					this.doc = MapDocument.getXMLDocument(xml);
					this.root = (Element) this.doc.getElementsByTagName("map")
							.item(0);
					this.container = new ModuleContainer();
					container.enableModules(getXMLDocument());
					Map map = new Map(xml,
							((InfoModule) this.container
									.getModule(InfoModule.class)).getInfo());
					loadedMaps.add(map);
				}
			}
		}
	}

	// checks if its loadable!

	public boolean isLoadable(File file, File xml) {
		if (new File(file, "region").exists() && xml.isFile() && xml.exists()
				&& new File(file, "level.dat").exists()) {
			return true;
		}
		return false;
	}

	// gets all the loaded maps (this will get every map even if they are in the
	// rotation)
	public List<Map> getLoadedMaps() {
		return this.loadedMaps;
	}

	/**
	 * 
	 * @param name
	 *            a Map Name
	 * @return returns a map your searching for
	 */
	public Map getMap(String name) {
		Map map = null;
		for (Map maps : this.getLoadedMaps()) {
			if (maps.getInfo().getName().equalsIgnoreCase((name))){
				map = maps;
			}
		}
		return map;
	}

	/**
	 * 
	 * @return returns the xml document
	 */
	public Document getXMLDocument() {
		return this.doc;
	}

	/**
	 * 
	 * @return the root element is "map"
	 */
	public Element getRootElement() {
		return this.root;
	}

	public ModuleContainer getModuleContainer() {
		return this.container;
	}

	public boolean hasNext() {
		return this.nextMap != null;
	}

	public void setNext(Map next) {
		this.nextMap = next;
	}

	public Map getNext() {
		return this.nextMap;
	}
}
