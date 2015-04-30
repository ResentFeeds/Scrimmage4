package me.skylertyler.scrimmage.team;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.ColorUtils;
import me.skylertyler.scrimmage.utils.Log;

import org.bukkit.ChatColor;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TeamParser extends ElementParser {

	private final List<Team> teams;
	private String teamsTag;
	private String teamTag; 
	public TeamParser(Element element, String teamsTag, String teamTag) {
		super(element);
		this.teams = new ArrayList<>(); 
		this.teamsTag = teamsTag;
		this.teamTag = teamTag;
		/**
		 * XML Parsing
		 */

		/**
		 * always add the observers no matter what the map is :)
		 */

		Team obs = new Team("Observers", "obs", ChatColor.AQUA,
				Integer.MAX_VALUE, TeamType.Observing);
		teams.add(obs);
		Node teamsNode = getElement().getElementsByTagName(this.teamsTag).item(0);
		if (teamsNode.getNodeType() == Node.ELEMENT_NODE) {
			Element teamsElement = (Element) teamsNode;
			NodeList list = teamsElement.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node teamNode = list.item(i);

				if (teamNode.getNodeType() == Node.ELEMENT_NODE
						&& teamNode.getNodeName().equals(this.teamTag)) {
					Element teamElement = (Element) teamNode;
					if (!teamElement.hasAttribute("max")) {
						Log.logWarning("check all the team tags for a max attribute!");
					}
					int max = Integer.parseInt(teamElement.getAttribute("max"));

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

	public List<Team> getTeams() {
		return this.teams;
	} 
}
