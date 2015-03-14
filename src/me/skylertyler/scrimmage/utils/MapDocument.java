package me.skylertyler.scrimmage.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MapDocument {

	public static Document doc;

	public static Document getXMLDocument(File file) throws SAXException,
			IOException, ParserConfigurationException {
		if (file.exists()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file); 
			return doc;
		}
		return null;
	}
}
