package me.skylertyler.scrimmage.kit;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import me.skylertyler.scrimmage.utils.Log;

public class Kit {

	private final String name;
	// need to make all these work -_-;
	private final List<ItemKit> items;
	// not working yet;
	private final List<ArmorKit> armor;

	// fix
	// will add armor kit , potion kit etc. :)
	public Kit(String name, List<ItemKit> items,
			@Nullable List<ArmorKit> armor) {
		this.name = name;
		this.items = items;
		this.armor = armor;
		Log.logInfo(toString());
	}

	public String getName() {
		return this.name;
	}

	public boolean hasName() {
		return this.getName() != null;
	}

	public List<ItemKit> getItems() {
		return this.items;
	}

	public boolean hasItems() {
		return this.getItems() != null || this.getItems().size() > 0;
	}

	public List<ArmorKit> getArmor() {
		return this.armor;
	}

	public boolean hasArmor() {
		return this.getArmor().size() > 1 || this.getArmor() != null;
	}

	@Override
	public String toString() {
		String result = null;
		for (ItemKit items : this.getItems()) {
			result = items.getStack().getType().name().replace("_", " ")
					.toUpperCase()
					+ " "
					+ items.getLore()
					+ " "
					+ items.getName()
					+ " "
					+ items.getSlot() + " ";
		}
		return "Kit [name=" + name + ", items=" + result + "]";
	}

	public void applyKit(Player player) {
		for (ItemKit items : getItems()) {
			if (items != null) {
				items.apply(player);
			}
		}
	}
}
