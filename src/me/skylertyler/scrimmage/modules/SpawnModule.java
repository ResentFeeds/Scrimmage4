package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.spawn.Spawn;
import me.skylertyler.scrimmage.team.Team;
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
			throw new NullPointerException("No spawns found on a map!");
		}
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		List<Spawn> spawns = parseSpawns(root, "spawns", "spawn");
		return new SpawnModule(spawns);
	}

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
					throw new NullPointerException("there is no team by '"
							+ topElement.getAttribute("team") + "'!");
				}
				
				
				
			}
		}

		spawns.add(spawn);
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
