package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.exception.SlotNotFoundException;
import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** the kit parser */
public class KitParser extends ElementParser {

	private final String kitsTag;
	private final String kitTag;
	private final List<Kit> kits;
	private List<ItemKit> items;
	private List<PotionKit> potions;

	public KitParser(Element element, String kitsTag, String kitTag) {
		super(element);
		/** all the kits */
		this.kits = new ArrayList<>();
		/** the "kits" tag */
		this.kitsTag = kitsTag;
		/** the "kit" tag */
		this.kitTag = kitTag;

		/**
		 * XML PARSING
		 */

		Node kitsTags = getElement().getElementsByTagName(this.kitsTag).item(0);
		if (kitsTags.getNodeType() == Node.ELEMENT_NODE) {
			Element kitsElement = (Element) kitsTags;
			NodeList listOfKits = kitsElement.getChildNodes();
			/** add the kits */
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
		this.potions = new ArrayList<>();
		// knockback reduction :)
		KnockbackReductionKit reduction = null;
		ArmorKit armor = null;
		String id = element.getAttribute("id");
		if (id == null) {
			Log.logWarning("No id found for a kit!");
			return null;
		}
		NodeList items = element.getChildNodes();

		/** items */
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

				/** armor */

				ItemStack[] armorKit = new ItemStack[4];
				/** do [4] because there are 4 armor slots for the armor */
				for (int a = 0; a < items.getLength(); a++) {
					Node node = items.item(a);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element nodeElement = (Element) node;
						if (XMLUtils.isValidArmorTag(nodeElement)) {
							switch (nodeElement.getTagName()) {
							case "helmet":
								armorKit[0] = parseItemStack(nodeElement);
								break;
							case "chestplate":
								armorKit[1] = parseItemStack(nodeElement);
								break;
							case "leggings":
								armorKit[2] = parseItemStack(nodeElement);
								break;
							case "boots":
								armorKit[3] = parseItemStack(nodeElement);
								break;
							}
						}
					}
				}
				armor = new ArmorKit(armorKit[0], armorKit[1], armorKit[2],
						armorKit[3]);
			}
		}

		/** potions may be null */
		for (int a = 0; a < items.getLength(); a++) {
			Node node = items.item(a);
			if (node.getNodeType() == Node.ELEMENT_NODE
					&& node.getNodeName().equals("potion")) {
				Element potionElement = (Element) node;
				this.potions.add(parsePotion(potionElement));
			}
		}

		List<String> parents = new ArrayList<>();
		if (element.hasAttribute("parents")) {
			parents = parseParents(element.getAttribute("parents"));
		}

		boolean forced = false;
		if (element.hasAttribute("forced")) {
			forced = XMLUtils.parseBoolean(element.getAttribute("forced"));
		}

		boolean potionparticles = false;
		if (element.hasAttribute("potion-particles")) {
			potionparticles = XMLUtils.parseBoolean(element
					.getAttribute("potion-particles"));
		}
		boolean discardbottles = true;
		if (element.hasAttribute("discard-potion-bottles")) {
			discardbottles = XMLUtils.parseBoolean(element
					.getAttribute("discard-potion-bottles"));
		}

		boolean resetenderpearls = false;
		if (element.hasAttribute("reset-ender-pearls")) {
			resetenderpearls = XMLUtils.parseBoolean(element
					.getAttribute("reset-ender-pearls"));
		}
		return new Kit(id, this.items, armor, this.potions, reduction, parents,
				forced, potionparticles, discardbottles, resetenderpearls);
	}

	public List<String> parseParents(String attribute) {
		List<String> parents = new ArrayList<>();
		/** split the list using a comma */
		String[] comma = attribute.split(",");

		for (String parent : comma) {
			/** check if there is any */
			if (parent != null) {
				parents.add(parent);
			}
		}

		return parents;
	}

	private PotionKit parsePotion(Element potionElement) {
		PotionEffectType type = XMLUtils.parsePotionEffect(potionElement
				.getTextContent());
		int duration = 1;
		int amplifier = 1;
		if (potionElement.hasAttribute("duration")) {
			duration = NumberUtils.parseInteger(potionElement
					.getAttribute("duration"));
		}

		if (potionElement.hasAttribute("amplifier")) {
			amplifier = NumberUtils.parseInteger(potionElement
					.getAttribute("amplifier"));
		}

		/** just make ambient false for now */
		return new PotionKit(type, duration, amplifier);
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

	public static EnchantKit parseEnchantKit(String enchant) {
		return new EnchantKit(enchant);
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
