package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.exception.NoSpawnsException;
import me.skylertyler.scrimmage.exception.TeamNotFoundException;
import me.skylertyler.scrimmage.spawn.Spawn;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.TeamUtils;

import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@ModuleInfo(name = "spawn", module = SpawnModule.class, requires = TeamModule.class)
public class SpawnModule extends Module {

	private List<Spawn> spawns = new ArrayList<Spawn>();

	public SpawnModule() {
		this.spawns = null;
	}

	public SpawnModule(List<Spawn> spawns) {
		this.spawns = spawns;
		int size = this.getSpawns().size();

		if (size == 0) {
			try {
				throw new NoSpawnsException(size);
			} catch (NoSpawnsException e) {
				// log a warning if the are no spawns!
				Log.logWarning(e.getMessage());
			}
		}
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		List<Spawn> spawns = parseSpawns(root, "spawns", "spawn");
		return new SpawnModule(spawns);
	}

	/**
	 * 
	 * @param root
	 *            root element
	 * @param topTag
	 *            "spawns"
	 * @param childTag
	 *            "spawn"
	 * @param def
	 *            default tag "defualt" for observers spawn!
	 * @return List<Spawn>
	 */
	private List<Spawn> parseSpawns(Element root, String topTag, String childTag) {
		List<Spawn> spawns = new ArrayList<Spawn>();
		Spawn spawn = null;
		NodeList children = root.getElementsByTagName(topTag).item(0)
				.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE
					&& node.getNodeName().equals(childTag)) {
				Element topElement = (Element) node;
				Team team = TeamUtils
						.matchTeam(topElement.getAttribute("team"));

				if (team == null) {
					try {
						throw new TeamNotFoundException(
								topElement.getAttribute("team"), null);
					} catch (TeamNotFoundException e) {
						e.printStackTrace();
					}
					return spawns;
				}

				/*
				 * float yaw = 0; if (topElement.hasAttribute("yaw")) { yaw =
				 * NumberUtils .parseFloat(topElement.getAttribute("yaw")); }
				 * 
				 * float pitch = 0;
				 * 
				 * if (topElement.hasAttribute("pitch")) { pitch =
				 * NumberUtils.parseFloat(topElement .getAttribute("pitch")); }
				 * 
				 * Kit kit = null; if (topElement.hasAttribute("kit")) { kit =
				 * KitUtils.getKitByName(topElement.getAttribute("kit")); }
				 * 
				 * BlockRegion region = null; if
				 * (topElement.hasAttribute("angle")) { region = new
				 * BlockRegion( LocationUtils.vectorFromString(topElement
				 * .getAttribute("angle"))); }
				 * 
				 * Region r = null; Node first_child =
				 * topElement.getFirstChild();
				 * Log.logInfo(first_child.getNodeName()); boolean valid =
				 * RegionUtils.isRegionTag(first_child);
				 * 
				 * if (valid) { r = RegionUtils.parseRegion(first_child); }
				 */
				spawn = new Spawn(team, null, null, null, 0, 0); 
				spawns.add(spawn);
			}
		}
		return spawns;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public List<Spawn> getSpawns() {
		return this.spawns;
	}
}
