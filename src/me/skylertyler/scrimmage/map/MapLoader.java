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

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class MapLoader {

	private List<Map> loadedMaps = null;

	private Element root;
	private Scrimmage scrim;

	private ModuleContainer container;

	public MapLoader(Scrimmage scrim) {
		this.scrim = scrim;
		this.loadedMaps = new ArrayList<Map>();
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
					this.container = new ModuleContainer();
					try {
						this.container.enableModules(MapDocument
								.getXMLDocument(xml));
					} catch (SAXException | IOException
							| ParserConfigurationException e) {
						e.printStackTrace();
					}

					InfoModule mnodule = (InfoModule) getContainer().getModule(
							InfoModule.class);
					Map newMap = new Map(maps, mnodule.getInfo());
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
}
