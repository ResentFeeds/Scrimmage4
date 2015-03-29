package me.skylertyler.scrimmage.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TestMap {

	private final File xml;
	private final File source;
	private final DocumentBuilderFactory factory;
	private final File path;  
	private DocumentBuilder builder;
	private Document doc;
	

	private String DEFUALT_XML = "map.xml"; 

	public TestMap(File source, File path, DocumentBuilderFactory factory) throws ParserConfigurationException, SAXException, IOException {
		this.source = source;
		this.path = path;
		this.factory = factory;
		this.builder = this.factory.newDocumentBuilder();
		this.xml = new File(source, DEFUALT_XML);
		this.doc = this.builder.parse(getXML());
	}
	
	public DocumentBuilder getBuilder(){
		return this.builder;
	}
	
	
	public Document getDocument(){
		return this.doc;
	}
	
	public File getPath(){
		return this.path;
	}

	public File getXML() {
		return xml;
	}

	public File getSource() {
		return source;
	}

	public DocumentBuilderFactory getFactory() {
		return factory;
	}

	public String getDEFUALT_XML() {
		return DEFUALT_XML;
	}
}
