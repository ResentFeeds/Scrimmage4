package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.exception.SlotNotFoundException;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.kit.KitItem;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

//TODO
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@ModuleInfo(name = "kits", module = KitModule.class, requires = TeamModule.class)
public class KitModule extends Module {

	private final List<Kit> kits;

	public KitModule() {
		this.kits = null;
	}

	public KitModule(List<Kit> kits) {
		this.kits = kits;
	}

	@Override
	public Module parse(Document doc) {
		Element root = doc.getDocumentElement();
		List<Kit> kit = parseKit(root, "kits", "kit");
		return new KitModule(kit);
	}

	public static List<Kit> parseKit(Element root, String kitsTag, String kitTag) {
		List<Kit> results = new ArrayList<>();
		Node kitsNode = root.getElementsByTagName(kitsTag).item(0);
		if (kitsNode != null) {
			if (kitsNode.getNodeType() == Node.ELEMENT_NODE) {
				Element kitsELement = (Element) kitsNode;
				NodeList children = kitsELement.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					Node kit = children.item(i);
					if (kit.getNodeType() == Node.ELEMENT_NODE
							&& kit.getNodeName().equals(kitTag)) {
						Element kitElement = (Element) kit;
						String name = kitElement.getAttribute("name");
						NodeList kids = kitElement.getChildNodes();
						for (int c = 0; c < kids.getLength(); c++) {
							Node node = kids.item(c);
							if (node.getNodeType() == Node.ELEMENT_NODE
									&& node.getNodeName().equals("item")) {
								Element itemElement = (Element) node;
								List<KitItem> items = parseItems(itemElement);
								Kit newKit = new Kit(name, items, null);
								results.add(newKit);
							}
						}
					}
				}
			}
		}
		return results;
	}

	public static KitItem parseItem(Element itemElement)
			throws SlotNotFoundException {
		int amount;
		if (itemElement.hasAttribute("amount")) {
			amount = NumberUtils.parseInteger(itemElement
					.getAttribute("amount"));
		} else {
			amount = 0;
		}

		int slot = NumberUtils.parseInteger(itemElement.getAttribute("slot"));
		boolean invalid = slot < 0 || slot > 35 && slot < 100 || slot > 103;
		if (invalid) {
			throw new SlotNotFoundException(slot);
		}

		Material mat = XMLUtils.parseMaterial(itemElement.getTextContent());
		KitItem newitem = new KitItem(slot, amount, mat);
		return newitem;
	}

	public static List<KitItem> parseItems(Element itemElement) {
		List<KitItem> items = new ArrayList<>();
		KitItem item = null;
		try {
			item = parseItem(itemElement);
		} catch (SlotNotFoundException e) {
			e.printStackTrace();
		}
		items.add(item);
		return items;
	}

	public List<Kit> getKits() {
		return this.kits;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

}
