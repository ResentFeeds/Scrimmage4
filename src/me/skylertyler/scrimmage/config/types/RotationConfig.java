package me.skylertyler.scrimmage.config.types;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.config.ConfigType;
import me.skylertyler.scrimmage.config.CoreConfig;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapLoader;
import me.skylertyler.scrimmage.utils.Log;

public class RotationConfig extends CoreConfig {

	private final File rotationYML;
	private List<Map> rotMaps = null;

	public RotationConfig(File rotationYML) {
		this.rotationYML = rotationYML;
	}

	public File getRotationYML() {
		return this.rotationYML;
	}

	@Override
	public void loadConfig() throws IOException {
		File rotation = this.getRotationYML();
		if (configExist()) {
			FileConfiguration config = YamlConfiguration
					.loadConfiguration(rotation);
			ConfigurationSection maps = config
					.getConfigurationSection("rotation");
			if (hasConfigurationSection(maps) && hasStringList(maps, "maps")) {
				this.setRotMaps(parseMaps(maps.getStringList("maps")));
			}
		}
	}


	private List<Map> parseMaps(List<String> stringList) {
		List<Map> maps = new ArrayList<Map>();
		Scrimmage scrimmage = Scrimmage.getScrimmageInstance();
		MapLoader loader = scrimmage.getLoader();
		for (int i = 0; i < stringList.size(); i++) {
			Map map = loader.getMap(stringList.get(i));
			if (loader.containsMap(map)) {
				maps.add(map);
				Log.logWarning("[Scrimmage4] added map " + map.getInfo().getName());
			} else {
				Log.logWarning("[Scrimmage4] there is no loaded map by the name of ");
			}
		}

		Log.logInfo(statusString(maps.size()));
		return maps;
	}

	public String statusString(int size) {
		String s = null;
		String grammer = null;
		if (size == 1){
			grammer = "is "; 
			s = "";
		} else if (size > 1 || size == 0) {
			grammer = "are ";
			s = "s";
		}
		return "There " + grammer + size + " map" + s + " loaded";
	}

	@Override
	public boolean configExist() {
		return getRotationYML().exists();
	}

	@Override
	public boolean createFile() throws IOException {
		return getRotationYML().createNewFile();
	}

	@Override
	public ConfigType getType() {
		return ConfigType.ROTATION;
	}

	public List<Map> getRotMaps() {
		return rotMaps;
	}

	public void setRotMaps(List<Map> rotMaps) {
		this.rotMaps = rotMaps;
	}

}
