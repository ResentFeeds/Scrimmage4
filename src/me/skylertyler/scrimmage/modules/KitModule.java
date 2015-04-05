package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.exception.SlotNotFoundException;
import me.skylertyler.scrimmage.kit.ItemKit;
import me.skylertyler.scrimmage.kit.Kit; 
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;


//TODO
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//FIx kits 
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
		List<Kit> kit = null;
		try {
			kit = parseKit(root, "kits", "kit", "item");
		} catch (SlotNotFoundException e) {
			e.printStackTrace();
		}
		return new KitModule(kit);
	}

	private List<Kit> parseKit(Element root, String kitsTag, String kitTag,
			String itemTag) throws SlotNotFoundException {
		List<Kit> all = new ArrayList<>();
		Node kits = root.getElementsByTagName(kitsTag).item(0);
		if (kits != null) {
			if (kits.getNodeType() == Node.ELEMENT_NODE) {
				Element kitsElement = (Element) kits;
				NodeList kitNodes = kitsElement.getChildNodes();
				for (int i = 0; i < kitNodes.getLength(); i++) {
					Node kit = kitNodes.item(i);
					if (kit.getNodeType() == Node.ELEMENT_NODE
							&& kit.getNodeName().equals(kitTag)) {
						Element kitElement = (Element) kit;
						String name = kitElement.getAttribute("name");

						if (name == null) {
							Log.logWarning("No name found for a kit!");
							return all;
						}

						NodeList items = kitElement.getChildNodes();
						for (int y = 0; y < items.getLength(); y++) {
							Node item = items.item(y);
							if (item.getNodeType() == Node.ELEMENT_NODE
									&& item.getNodeName().equals(itemTag)) {
								Element itemElement = (Element) item;
								List<ItemKit> kitItems = parseItems(itemElement);
								Kit newKit = new Kit(name, kitItems);
								all.add(newKit);
							}
						}
					}
				}
			}
		}
		return all;
	}

	/*
	 * public static List<Kit> parseKit(Element root, String kitsTag, String
	 * kitTag) { List<Kit> results = new ArrayList<>(); Node kitsNode =
	 * root.getElementsByTagName(kitsTag).item(0); if (kitsNode != null) { if
	 * (kitsNode.getNodeType() == Node.ELEMENT_NODE) { Element kitsELement =
	 * (Element) kitsNode; NodeList children = kitsELement.getChildNodes(); for
	 * (int i = 0; i < children.getLength(); i++) { Node kit = children.item(i);
	 * if (kit.getNodeType() == Node.ELEMENT_NODE &&
	 * kit.getNodeName().equals(kitTag)) { Element kitElement = (Element) kit;
	 * String name = kitElement.getAttribute("name"); NodeList childs =
	 * kitElement.getChildNodes(); for (int y = 0; y < childs.getLength(); y++)
	 * { Node item = childs.item(y); if (item.getNodeType() == Node.ELEMENT_NODE
	 * && item.getNodeName().equals("item")) { Element newItem = (Element) item;
	 * List<KitItem> items = null; try { items = parseItems(newItem); } catch
	 * (SlotNotFoundException e) { e.printStackTrace(); } Kit kits = new
	 * Kit(name, items, null); results.add(kits); } } } } } } return results; }
	 */

	public static ItemKit parseItem(Element itemElement)
			throws SlotNotFoundException {
		// when the amount is 0 it will turn to 1 :)
		// look at the KitItem constructor before telling me this is an error :)
		int amount;
		if (itemElement.hasAttribute("amount")) {
			amount = NumberUtils.parseInteger(itemElement
					.getAttribute("amount"));
		} else {
			amount = 0;
		}

		int slot = NumberUtils.parseInteger(itemElement.getAttribute("slot"));
		// will be invalid if the slot (id) is less than 0 or greater then 35 &
		// below 100 or greater than 103 :)
		boolean invalid = slot < 0 || slot > 35 && slot < 100 || slot > 103;
		if (invalid) {
			throw new SlotNotFoundException(slot);
		}

		Material mat = XMLUtils.parseMaterial(itemElement.getTextContent());

		String lore = null;
		if (itemElement.hasAttribute("lore")) {
			lore = itemElement.getAttribute("lore");
		}

		String name = null;
		if (itemElement.hasAttribute("name")) {
			name = itemElement.getAttribute("name");
		}

		int damage;
		if(itemElement.hasAttribute("damage")){
			damage = NumberUtils.parseInteger(itemElement.getAttribute("damage"));
		}else{
			damage = 0;
		}
		String enchantment = null;
		if (itemElement.hasAttribute("enchantment")) {
			enchantment = itemElement.getAttribute("enchantment");
		}

		return new ItemKit(slot, mat, amount, damage,name, lore, enchantment);
	}

	public static List<ItemKit> parseItems(Element itemElement)
			throws SlotNotFoundException {
		List<ItemKit> items = new ArrayList<>();
		ItemKit item = parseItem(itemElement);
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
