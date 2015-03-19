package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.author.Author;
import me.skylertyler.scrimmage.contributor.Contributor;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.rules.Rule;
import me.skylertyler.scrimmage.utils.BukkitUtils;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.version.Version;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//TODO fix this and change the mapLoader! stuff!
@ModuleInfo(name = "info", desc = "basic information about the current map!", module = InfoModule.class)
public class InfoModule extends Module {

	protected MapInfo info;

	public InfoModule() {
		this.info = null;
	}

	public InfoModule(MapInfo info) {
		this.info = info;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	@Override
	public Module parse(Document doc) {
		MapInfo info = parseInfo(doc);
		return new InfoModule(info);
	}

	private MapInfo parseInfo(Document doc) {
		// map name
		Node nameNode = doc.getElementsByTagName("name").item(0);
		if (nameNode == null) {
			Log.logWarning("a map needs a name!");
		}

		String name = nameNode.getTextContent();

		// objective

		Node objectiveNode = doc.getElementsByTagName("objective").item(0);
		if (objectiveNode == null) {
			Log.logWarning("a map needs a objective!");
		}

		String objective = objectiveNode.getTextContent();

		// version
		Version version = parseVersion(doc.getElementsByTagName("version")
				.item(0));

		String versionFormat = version.toString();
		Log.logInfo(versionFormat);

		// contributors
		List<Contributor> contributors = contributorList(
				doc.getDocumentElement(), "contributors", "contributor");

		// authors
		List<Author> authors = authorList(doc.getDocumentElement(), "authors",
				"author");

		// rules

		List<Rule> rules = ruleList(doc.getDocumentElement(), "rules", "rule");
		return new MapInfo(name, version, authors, contributors, rules,
				objective);
	}

	public static List<Contributor> contributorList(Element root,
			String topLevelTag, String tag) {
		List<Contributor> contribs = new ArrayList<Contributor>();
		Node node = root.getElementsByTagName(topLevelTag).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element contributorElement = (Element) node;
				NodeList list = contributorElement.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node contributorNode = list.item(i);
					if (contributorNode.getNodeType() == Node.ELEMENT_NODE
							&& contributorNode.getNodeName().equals(tag)) {
						Element contribElement = (Element) contributorNode;
						String contributor = contribElement.getTextContent();
						if (contribElement.hasAttribute("contribution")) {
							String contribution = contribElement
									.getAttribute("contribution");
							contribs.add(new Contributor(contribution,
									contributor));
						} else {
							contribs.add(new Contributor(contributor));
						}
					}
				}

			}
		}
		return contribs;
	}

	public static List<Author> authorList(Element root, String topLevelTag,
			String tag) {
		List<Author> authors = new ArrayList<Author>();
		Node node = root.getElementsByTagName(topLevelTag).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList list = element.getChildNodes();
				for (int i = 0; i < list.getLength(); i++) {
					Node author = list.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE
							&& node.getNodeName().equals(tag)) {
						Element authorElement = (Element) author;
						String name = authorElement.getTextContent();
						String uuid = authorElement.getAttribute("uuid");
						if (authorElement.getAttribute("contribution") != null) {
							String contribution = authorElement
									.getAttribute("contribution");
							authors.add(new Author(name, contribution, uuid));
						} else {
							authors.add(new Author(name, uuid));
						}
					}
				}
			}
		}
		return authors;
	}

	public static List<Rule> ruleList(Element root, String topTag,
			String childTag) {
		List<Rule> rules = new ArrayList<Rule>();
		Node node = root.getElementsByTagName(topTag).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element nodeElement = (Element) node;
				NodeList children = nodeElement.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					Node rule = children.item(i);
					if (rule.getNodeType() == Node.ELEMENT_NODE
							&& rule.getNodeName().equals(childTag)) {
						Element ruleElement = (Element) rule;
						String ruleText = BukkitUtils.colorize(ruleElement
								.getTextContent());
						rules.add(new Rule(ruleText));
					}
				}
			}
		}
		return rules;
	}

	// need to fix this!
	// some reason its this is giving me a error -_-
	public static Version parseVersion(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String[] parts = element.getTextContent().split(".");
			if (parts.length <= 3) {
				int major = Integer.parseInt(parts[0]);
				int minor = Integer.parseInt(parts[1]);
				int patch = Integer.parseInt(parts[2]);
				return new Version(major, minor, patch);
			}
		}
		return null;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(getInfo().getName() + " is the name!");
	}

	public MapInfo getInfo() {
		return this.info;
	}
}
