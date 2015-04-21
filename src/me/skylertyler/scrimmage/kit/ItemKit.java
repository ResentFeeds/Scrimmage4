package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

import me.skylertyler.scrimmage.utils.BukkitUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class ItemKit {
 
	// need to work on getting the enchants to work -_-
	private final int damage;
	private final String name;
	private final String lore;
	private final ItemStack stack;
	private final int slot;
	private final ItemMeta itemMeta;

	private Optional<EnchantKit> enchant;
	private Optional<String> color;

	public ItemKit(int slot, ItemStack stack, int damage, String name,
			String lore, @Nullable EnchantKit enchant, @Nullable String color) {
		this.slot = slot;
		this.stack = stack;
		this.itemMeta = getStack().getItemMeta();
		this.damage = damage;
		this.name = name;
		this.lore = lore;
		this.enchant = Optional.fromNullable(enchant);
		this.color = Optional.fromNullable(color);

		/**
		 * this means if the enchantment attribute is presnt and the value of
		 * the attribute is = "", or not there it wont do this
		 */
		if (this.enchant.isPresent()) {
			/** try */
			this.getStack().addEnchantments(
					this.enchant.get().getEnchantments());
		}

		if (this.damage != 0) {
			getStack().setDurability((short) this.getDamage());
		}

		// works fine ;)
		String newName = null;
		if (hasName()) {
			newName = parseName(getName());
		} else {
			newName = this.getStack().getType().name().replace("_", " ")
					.toLowerCase();
		}
 
		/** lore */
		List<String> lores = new ArrayList<>();
		if (hasLore()) {
			lores = parseLore(getLore());
		}

		/** setting display name & lore */
		getItemMeta().setDisplayName(newName);
		getItemMeta().setLore(lores);

		// color ?
		if (getColor().isPresent()) {
			if (this.itemMeta instanceof LeatherArmorMeta) {
				LeatherArmorMeta armorMeta = (LeatherArmorMeta) this.itemMeta;
				armorMeta.setColor(XMLUtils.applyColor(getColor().get()));
				this.getStack().setItemMeta(armorMeta);
			}
		}
		getStack().setItemMeta(getItemMeta());
	}

	public boolean hasName() {
		return this.getName() != null;
	}

	public boolean hasLore() {
		return this.getLore() != null;
	}

	public int getDamage() {
		return this.damage;
	}

	public String getName() {
		return this.name;
	}

	public String getLore() {
		return this.lore;
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public int getSlot() {
		return this.slot;
	}

	/** apply this item to the player */
	public void apply(Player player) {
		PlayerInventory pi = player.getInventory();
		pi.setItem(this.slot, this.stack);
	}

	public boolean hasEnchant() {
		return this.enchant != null;
	}

	public ItemMeta getItemMeta() {
		return this.itemMeta;
	}

	/** parsing lore */ 
	public List<String> parseLore(String lore) {
		List<String> lores = ImmutableList.copyOf(Splitter.on("|").split(lore));
		List<String> coloredLore = BukkitUtils.colorizeList(lores);
		return coloredLore;
	}

	/** parsing name */
	public String parseName(String name) {
		// if it contains the ` it will have color -_- using the BukkitUtils
		// colorize(string) method :)
		boolean hasColor = name.contains("`") || name.contains("§");
		String format = null;
		if (hasColor) {
			format = BukkitUtils.colorize(name);
			// else
		} else {
			// name will be a defualt name with no color (white)
			format = name;
		}

		String result = format;
		return result;
	}

	public Optional<String> getColor() {
		return this.color;
	}

	public boolean hasColor() {
		return this.getColor() != null;
	}
}
