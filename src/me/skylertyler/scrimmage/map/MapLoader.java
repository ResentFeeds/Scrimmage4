package me.skylertyler.scrimmage.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.modules.ModuleContainer;
import me.skylertyler.scrimmage.utils.ConsoleUtils;
import me.skylertyler.scrimmage.utils.MapDocument;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class MapLoader {

	private final List<Map> loadedMaps;

	private Element root;
	private final Scrimmage scrim;

	private ModuleContainer container;

	private List<String> mapNames = new ArrayList<>();

	public MapLoader(Scrimmage scrim) {
		this.scrim = scrim;
		this.loadedMaps = new ArrayList<Map>();
	}

	public void loadMaps() {
		File rot = Scrimmage.getScrimmageInstance().getRotationFile();
		boolean exist = rot.exists();
		if (exist) {
			for (File source : rot.listFiles()) {
				if (source != null && source.isDirectory()) {
					File xml, region, level;
					xml = new File(source, "map.xml");
					region = new File(source, "region");
					level = new File(source, "level.dat");
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
						MapInfo info = ((InfoModule) this.getContainer()
								.getModule(InfoModule.class)).getInfo();
						Map newMap = new Map(xml, source, info);
						addMap(newMap);
					}
				}
			}

			String format = null;
			for (Map map : getLoadedMaps()) {
				if (map != null) {
					format = map.getInfo().getName() + " has been loaded!";
				}
				ConsoleUtils.sendConsoleMessage(format);
			}
		}
	}

	public void addMap(Map map) {
		this.loadedMaps.add(map);
		this.mapNames.add(map.getInfo().getName());
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
	// get a map by the file name or name in the xml :)
	public Map getMap(String name) {
		Map map = null;
		for (Map maps : this.getLoadedMaps()) {
			if (maps.getInfo().getName().equalsIgnoreCase((name))
					|| maps.getSourceName().equalsIgnoreCase(name)) {
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

	public List<String> getMapNames() {
		return mapNames;
	}
}
