package me.skylertyler.scrimmage.map;

import java.io.File;

import org.w3c.dom.Document;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.ModuleContainer;

public class Map {

	private File xml;
	private MapInfo info;
	private Scrimmage scrim;
	private File source;
	private File path;
	private String DEFUALT_XML = "map.xml";
	private Document doc;
	private ModuleContainer container; 
 
	/*public Map(File xml, MapInfo mapInfo) {
		this.xml = xml;
		this.info = mapInfo;
	}
	*/
	
	public Map(File source, MapInfo info){ 
		this.source = source; 
		this.info = info;
		this.xml = new File(source, DEFUALT_XML);
	}
 
	
	public String getDefualtXML(){
		return this.DEFUALT_XML;
	}
	
	public Document getDocument(){
		return this.doc;
	}
	
	public ModuleContainer getModuleContainer(){
		return this.container;
	}
	/**
	 * 
	 * @return the xml file
	 */
	public File getXMLFile() {
		return this.xml;
	}

	/**
	 * 
	 * @return the map info for the current map !
	 */

	public MapInfo getInfo() {
		return this.info;
	}


	public File getXml() {
		return xml;
	}


	public Scrimmage getScrim() {
		return scrim;
	}


	public File getSource() {
		return source;
	}


	public File getPath() {
		return path;
	} 
 
}
