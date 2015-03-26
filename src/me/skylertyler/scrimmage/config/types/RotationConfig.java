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
			if (hasConfigurationSection(maps)
					&& maps.getStringList("maps") != null) {
				this.setRotMaps(parseMaps(maps.getStringList("maps")));
			}
		}
	}

	private List<Map> parseMaps(List<String> stringList) {
		List<Map> maps = new ArrayList<Map>();
		Scrimmage scrimmage = Scrimmage.getScrimmageInstance();
		MapLoader loader = scrimmage.getLoader();
		Map map = loader.getMap(stringList.get(0));
		if (loader.getLoadedMaps().contains(map)) {
			maps.add(map);
			Log.logInfo(statusString(maps.size()));
		} else {
			Log.logWarning("[Scrimmage4] there is no loaded map by the name of"
					+ " idk");
		}
		return maps;
	}

	private String statusString(int size) {
		String s = null;
		 if(size != 1){
			 s = " s ";
		 }else{
			 s = " ";
		 }
		return "There is "+ size + " map"+ s + "loaded"; 
	}

	@Override
	public boolean hasConfigurationSection(
			ConfigurationSection configurationSection) {
		return configurationSection != null ? true : false;
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

	@Override
	public boolean hasType() {
		return this.getType() != null ? true : false;
	}

	public List<Map> getRotMaps() {
		return rotMaps;
	}

	public void setRotMaps(List<Map> rotMaps) {
		this.rotMaps = rotMaps;
	}

}
