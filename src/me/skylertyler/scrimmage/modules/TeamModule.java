package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamType;
import me.skylertyler.scrimmage.utils.ColorUtils;
import me.skylertyler.scrimmage.utils.Log;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@ModuleInfo(name = "team", desc = "team module", module = TeamModule.class)
public class TeamModule extends Module {

	protected List<Team> teams;

	public TeamModule() {
		this.teams = null;
	}

	public TeamModule(List<Team> teams) {
		this.teams = teams; 
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		List<Team> teams = parseTeams(root, "teams", "team");
		return new TeamModule(teams);
	}

	public List<Team> parseTeams(Element root, String parent, String child) {
		List<Team> teams = new ArrayList<Team>();
		Team obs = new Team("Observers", "obs", ChatColor.AQUA,
				Integer.MAX_VALUE, TeamType.Observing);
		teams.add(obs);
		Node node = root.getElementsByTagName(parent).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element parentElement = (Element) node;
				// teams here
				NodeList list = parentElement.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node team = list.item(i);
					if (team.getNodeType() == Node.ELEMENT_NODE
							&& team.getNodeName().equals(child)) {
						Element teamElement = (Element) team;

						if (!teamElement.hasAttribute("max")) {
							Log.logWarning("check all the team tags for a max attribute!");
						}
						int max = Integer.parseInt(teamElement
								.getAttribute("max"));

						// checks the max if its not less or equal to zero
						if (max <= 0) {
							Log.logWarning("the max cant be " + max + "!");
						}

						int overfill = Integer.parseInt(teamElement
								.getAttribute("max-overfill"));

						// checks the overfill is less than the max if it is it
						// will send them this message!
						if (overfill <= max) {
							Log.logWarning("the overfill cant be less than or equal to the max!");
						}

						String name = teamElement.getTextContent();

						// checks if the name is empty
						if (name.isEmpty()) {
							Log.logWarning("the name cant be empty!");
						}

						// checks if it has the color attribute
						if (!teamElement.hasAttribute("color")) {
							Log.logWarning("check all the team tags for a color attribute");
						}

						ChatColor color = ColorUtils.parseChatColor(teamElement
								.getAttribute("color"));

						String id = teamElement.getAttribute("id");
						// check if the id attribute is empty!
						// if it is it will give them this error / message -_-
						if (id.isEmpty()) {
							Log.logWarning("the team id attribute cant be empty or null");
						}

						Team newTeam = null;
						// checks if the team node has the attribut e
						// 'max-overfill'
						if (teamElement.hasAttribute("max-overfill")) {
							newTeam = new Team(name, id, color, max, overfill,
									TeamType.Participating);
						} else {
							newTeam = new Team(name, id, color, max,
									TeamType.Participating);
						}
						teams.add(newTeam);
					}
				}
			}
		} 
		return teams;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public List<Team> getTeams() {
		return this.teams;
	}
}
