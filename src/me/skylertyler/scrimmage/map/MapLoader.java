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
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class MapLoader {

	private List<Map> loadedMaps = null;

	private Element root;
	private Scrimmage scrim;

	private ModuleContainer container;

	public MapLoader(Scrimmage scrim) {
		this.scrim = scrim;
		this.loadedMaps = new ArrayList<Map>();
		this.container = new ModuleContainer();
	}

	public void loadMaps() {
		File rot = Scrimmage.getScrimmageInstance().getRotationFile();
		for (File maps : rot.listFiles()) {
			if (maps != null && maps.isDirectory()) {
				File xml, region, level;
				xml = new File(maps, "map.xml");
				region = new File(maps, "region");
				level = new File(maps, "level.dat");
				boolean validXML = xml.isFile() && !xml.isHidden()
						&& !xml.isDirectory();
				boolean validREGION = region.isDirectory()
						&& !region.isHidden();
				boolean validLEVEL = level.isFile() && !level.isHidden()
						&& !level.isDirectory();
				boolean loadable = validXML && validREGION && validLEVEL;
				if (loadable) {
					Document doc = null;
					try {
						doc = MapDocument.getXMLDocument(xml);
					} catch (SAXException | IOException
							| ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.container.enableModules(doc);
					// try to fix the map constructer to get rid of the / map
					// info and add enable modules to the match constructer to
					// enable the modules for the current map not for all the
					// maps that are in the rotation or loaded?
					MapInfo info = ((InfoModule) this.getContainer().getModule(InfoModule.class)).getInfo();
					Map newMap = new Map(maps, info);
					addMap(newMap);
				}
			}
		}
	}

	public void addMap(Map map) {
		this.loadedMaps.add(map);
	}

	// checks if its loadable!

	public boolean isLoadable(File file) {
		if (new File(file, "region").exists()
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
			if (maps.getInfo().getName().equalsIgnoreCase((name))) {
				map = maps;
			}
		}
		return map;
	}

	/**
	 * 
	 * @return the root element is "map"
	 */
	public Element getRootElement() {
		return this.root;
	}

	public boolean containsMap(Map map) {
		return this.getLoadedMaps().contains(map) ? true : false;
	}

	public Scrimmage getScrim() {
		return this.scrim;
	}

	public ModuleContainer getContainer() {
		return this.container;
	}

	public static boolean hasNode(Node node) {
		return node != null ? true : false;
	}
}
