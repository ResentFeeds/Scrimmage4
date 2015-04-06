package me.skylertyler.scrimmage.kit;

import java.util.List;

import me.skylertyler.scrimmage.utils.Log;

public class Kit {

	private final String name;
	private final List<ItemKit> items;

	// will add armor kit , potion kit etc. :)
	public Kit(String name, List<ItemKit> items) {
		this.name = name;
		this.items = items;
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

	@Override
	public String toString() {
		String result = null;
		for (ItemKit items : this.getItems()) {
			result = items.getMaterial().name().replace("_", " ").toUpperCase()
					+ " " + items.getLore() + " " + items.getName() + " "
					+ items.getSlot() + " ";
		}
		return "Kit [name=" + name + ", items=" + result + "]";
	} 
}
