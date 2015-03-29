package me.skylertyler.scrimmage.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.xml.sax.SAXException;

public class TestLoader {

	//working around to fix the loader -_- (before any more additions to the scrimmage plugin!
	public List<TestMap> loadedMaps = null;

	public TestLoader() {
		this.loadedMaps = new ArrayList<TestMap>();
	}

	public void loadMaps() throws ParserConfigurationException {
		File file = Scrimmage.getScrimmageInstance().getRotationFile();
		boolean exist = file.exists();
		if (exist) {
			for (File maps : file.listFiles()) { 
				if (maps.isDirectory()) {
					if (maps != null) {
						DocumentBuilderFactory factory = DocumentBuilderFactory
								.newInstance(); 
						TestMap testing = null;
						try {
							testing = new TestMap(maps,
									maps.getParentFile(), factory);
						} catch (SAXException | IOException e) { 
							e.printStackTrace();
						} 
						loadedMaps.add(testing); 
					}
				}
			}
			
			
			for(TestMap test : loadedMaps){
				 if(test != null){
                 String name = test.getDocument().getElementsByTagName("name").item(0).getTextContent();
					String message = test.getSource().getName()  + " is the source for " + name;
					ConsoleUtils.sendConsoleMessage(message);
				 }
			}
		} 
	}
}