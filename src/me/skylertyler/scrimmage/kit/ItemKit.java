package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import me.skylertyler.scrimmage.utils.BukkitUtils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class ItemKit {

	// but its still giving me the very bottom <item> tag item in the kit -_- (need to work on EnchantKit) wondering why it isnt working how i wanted it to > D:
	private final int damage;
	private final String name;
	private final String lore;
	private final ItemStack stack;
	private final int slot;
	private final EnchantKit enchant;
	private final ItemMeta itemMeta;

	public ItemKit(int slot, ItemStack stack, int damage, String name,
			String lore, EnchantKit enchant) {
		this.slot = slot;
		this.stack = stack;
		this.itemMeta = getStack().getItemMeta();
		this.damage = damage;
		this.name = name;
		this.lore = lore;
		this.enchant = enchant;

		if (hasEnchant()) {
			for (Entry<Enchantment, Integer> entry : getEnchant()
					.getEnchantments().entrySet()) {
				Enchantment enchantment = entry.getKey();
				Integer level = entry.getValue(); 
				if (level <= 4) {
					getStack().addEnchantment(enchantment, level);
				}

				if (level >= 5) {
					this.getStack().addUnsafeEnchantment(enchantment, level);
				}
			}
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

		// fix lore -_-
		List<String> lores = null;
		if (hasLore()) {
			lores = parseLore(getLore());
		} else {
			lores = new ArrayList<>();
		}

		getItemMeta().setDisplayName(newName);
		getItemMeta().setLore(lores);
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

	public void apply(Player player) {
		PlayerInventory pi = player.getInventory();
		int slot = this.getSlot();
		ItemStack stack = this.getStack();
		pi.setItem(slot, stack);
	}

	public EnchantKit getEnchant() {
		return this.enchant;
	}

	public boolean hasEnchant() {
		return this.enchant != null;
	}

	public ItemMeta getItemMeta() {
		return this.itemMeta;
	}

	// working lorses :D
	public List<String> parseLore(String lore) {
		List<String> lores = ImmutableList.copyOf(Splitter.on("|").split(lore));
		List<String> coloredLore = BukkitUtils.colorizeList(lores);
		return coloredLore;
	}

	// working name :)
	public String parseName(String name) {
		// if it contains the ` it will have color -_- using the BukkitUtils colorize(string) method :)
		boolean hasColor = name.contains("`");
		String format = null;
		if (hasColor) {
			format = BukkitUtils.colorize(name);
			//else 
		} else {
			// name will be a defualt name with no color (white) 
			format = name;
		}

		String result = format;
		return result;
	}
}
