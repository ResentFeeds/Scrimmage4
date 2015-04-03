package me.skylertyler.scrimmage.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;

import me.skylertyler.scrimmage.author.Author;
import me.skylertyler.scrimmage.utils.MessageUtils;
import static org.bukkit.ChatColor.*;

public class Map {

	private final File xml;
	private final MapInfo info;

	public Map(File xml, MapInfo mapInfo) {
		this.xml = xml;
		this.info = mapInfo;

	}

	public boolean notNull() {
		return this != null;
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

	/**
	 * 
	 * @returns a short description about the map!
	 */
	public String getShortMapDescription() {
		String currently = LIGHT_PURPLE + "Currently Playing";
		String name = getInfo().getName();
		String by = "by";
		String mapAuthors = MessageUtils
				.authorList(getAuthors().listIterator());
		String format = currently + " " + name + " " + by + " " + mapAuthors;
		return format;
	}

	public List<String> getAuthors() {
		List<String> authors = new ArrayList<String>();
		authors.clear();
		String name = null;
		Player player = null;
		for (Author author : getInfo().getAuthors()) {
			Preconditions.checkArgument(author.hasContribution());
			Preconditions.checkNotNull(author.getUUID(), "null uuid");
			if (author.hasUUID()) {
				player = Bukkit.getPlayer(author.getUUID());
				name = player.getName();
				authors.add(name);
			}
		}

		return authors;
	}
}
