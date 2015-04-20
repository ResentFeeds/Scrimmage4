package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.exception.SlotNotFoundException;
import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KitParser extends ElementParser {

	private final String kitsTag;
	private final String kitTag;
	private final List<Kit> kits;
	private List<ItemKit> items;

	public KitParser(Element element, String kitsTag, String kitTag) {
		super(element);
		this.kits = new ArrayList<>();
		this.kitsTag = kitsTag;
		this.kitTag = kitTag;

		/**
		 * XML PARSING
		 */

		Node kitsTags = getElement().getElementsByTagName(this.kitsTag).item(0);
		if (kitsTags.getNodeType() == Node.ELEMENT_NODE) {
			Element kitsElement = (Element) kitsTags;
			NodeList listOfKits = kitsElement.getChildNodes();

			for (int i = 0; i < listOfKits.getLength(); i++) {
				Node node = listOfKits.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE
						&& node.getNodeName().equals(this.kitTag)) {
					Element kitElement = (Element) node;
					Kit kit = this.parseKit(kitElement);
					this.kits.add(kit);
				}
			}
		}
	}

	public Kit parseKit(Element element) {
		this.items = new ArrayList<>();
		// knockback reduction :)
		KnockbackReductionKit reduction = null;
		String name = element.getAttribute("name");
		if (name == null) {
			Log.logWarning("No name found for a kit!");
			return null;
		}
		NodeList items = element.getChildNodes();
		// items ?
		for (int y = 0; y < items.getLength(); y++) {
			Node item = items.item(y);
			if (item.getNodeType() == Node.ELEMENT_NODE
					&& item.getNodeName().equals("item")) {
				Element itemElement = (Element) item;
				ItemKit newItemKit = null;
				try {
					newItemKit = parseItem(itemElement);
				} catch (SlotNotFoundException e) {
					e.printStackTrace();
				}
				this.items.add(newItemKit);
				for (int f = 0; f < items.getLength(); f++) {
					Node node = items.item(f);
					if (node.getNodeName().equals("knockback-reduction")
							&& node.getNodeType() == Node.ELEMENT_NODE) {
						Element reductionElement = (Element) node;
						reduction = parseReductionKit(reductionElement);
					}
				}
			}
		}
		String kit = null;
		if (element.hasAttribute("parent")) {
			kit = element.getAttribute("parent");
		}
		return new Kit(name, this.items, null, reduction, kit);
	}

	private KnockbackReductionKit parseReductionKit(Element element) {
		String text = element.getTextContent();
		float reduction = NumberUtils.parseFloat(text);
		return new KnockbackReductionKit(reduction);
	}

	public static ItemKit parseItem(Element itemElement)
			throws SlotNotFoundException {
		// when the amount is 0 it will turn to 1 :)
		int slot = NumberUtils.parseInteger(itemElement.getAttribute("slot"));
		// will be invalid if the slot (id) is less than 0 or greater then 35 &
		// below 100 or greater than 103 :)
		boolean invalid = slot < 0 || slot > 35 && slot < 100 || slot > 103;
		if (invalid) {
			throw new SlotNotFoundException(slot);
		}

		ItemStack stack = parseItemStack(itemElement);

		String lore = null;
		if (itemElement.hasAttribute("lore")) {
			lore = itemElement.getAttribute("lore");
		}

		String name = null;
		if (itemElement.hasAttribute("name")) {
			name = itemElement.getAttribute("name");
		}
		int damage = 0;
		if (itemElement.hasAttribute("damage")) {
			damage = NumberUtils.parseInteger(itemElement
					.getAttribute("damage"));
		}

		EnchantKit enchant = null;
		if (itemElement.hasAttribute("enchantment")) {
			enchant = parseEnchantKit(itemElement.getAttribute("enchantment"));
		}

		String color = null;

		if (itemElement.hasAttribute("color")) {
			color = itemElement.getAttribute("color");
		}

		return new ItemKit(slot, stack, damage, name, lore, enchant, color);
	}

	public static ItemStack parseItemStack(Element element) {
		Material mat = XMLUtils.parseMaterial(element.getTextContent());
		int amount = 1;
		if (element.hasAttribute("amount")) {
			amount = NumberUtils.parseInteger(element.getAttribute("amount"));
		}
		return new ItemStack(mat, amount);
	}

	public static EnchantKit parseEnchantKit(@Nullable String enchant) {
		if (enchant != null) {
			return new EnchantKit(enchant);
		} else {
			return null;
		}
	}

	public Element getElement() {
		return this.element;
	}

	public String getKitsTag() {
		return this.kitsTag;
	}

	public String getKitTag() {
		return this.kitTag;
	}

	public List<Kit> getKits() {
		return this.kits;
	}
}
