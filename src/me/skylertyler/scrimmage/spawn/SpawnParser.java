package me.skylertyler.scrimmage.spawn;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.exception.TeamNotFoundException;
import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.TeamUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// parser
public class SpawnParser extends ElementParser {

	private final List<Spawn> spawns;
	private final String spawnsTag;
	private final String spawnTag;
	private final String defaultTag;

	/**
	 * 
	 * @param element
	 *            the map element
	 * @param spawnsTag
	 *            the "spawns" tag
	 * @param spawnTag
	 *            the "spawn" tag
	 * @param defaultTag
	 *            the "default" tag
	 */
	public SpawnParser(Element element, String spawnsTag, String spawnTag,
			String defaultTag) {
		super(element);
		// always have a new ArrayList
		this.spawns = new ArrayList<>();
		this.spawnsTag = spawnsTag;
		this.spawnTag = spawnTag;
		this.defaultTag = defaultTag;

		/**
		 * XML PARSING
		 */

		Node spawnsNode = element.getElementsByTagName(spawnsTag).item(0);
		if (spawnsNode.getNodeType() == Node.ELEMENT_NODE) {
			Element spawnsElement = (Element) spawnsNode;
			NodeList allSpawns = spawnsElement.getChildNodes();
			for (int i = 0; i < allSpawns.getLength(); i++) {
				Node spawnNode = allSpawns.item(i);
				if (spawnNode.getNodeType() == Node.ELEMENT_NODE
						&& spawnNode.getNodeName().equals(spawnTag)) {
					Element spawnElement = (Element) spawnNode;
					Spawn spawn = parseSpawn(spawnElement);
					spawns.add(spawn);
				}
			}
		}
	}

	public Spawn parseSpawn(Element spawnElement) {
		Team team = TeamUtils.getTeam(spawnElement.getAttribute("team"));
		if (team == null) {
			try {
				throw new TeamNotFoundException(
						spawnElement.getAttribute("team"), null);
			} catch (TeamNotFoundException e) {
				e.printStackTrace();
			}
		}

		String kit = "";

		if (spawnElement.hasAttribute("kit")) {
			kit = spawnElement.getAttribute("kit");
		}

		boolean bedspawn = false;
		if (spawnElement.hasAttribute("bedspawn")) {
			bedspawn = XMLUtils.parseBoolean(spawnElement
					.getAttribute("bedspawn"));
		}

		Region region = null;
		NodeList children = spawnElement.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (RegionUtils.isRegionTag(node)) {
					region = RegionUtils.parseRegion(node);
				}
			}
		}
		return new Spawn(team, region, kit, null, 0, 0);
	}

	/**
	 * getters
	 */

	public List<Spawn> getSpawns() {
		return this.spawns;
	}

	public String getSpawnsTag() {
		return spawnsTag;
	}

	public String getSpawnTag() {
		return spawnTag;
	}

	public String getDefaultTag() {
		return defaultTag;
	}

}
