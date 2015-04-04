package me.skylertyler.scrimmage.kit;

import java.util.List;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.utils.Log;

public class Kit {

	private final String name;
	private final List<KitItem> items;
	private final List<KitPotion> potions;

	public Kit(String name, List<KitItem> items,
			@Nullable List<KitPotion> potions) {
		this.name = name;
		this.items = items;
		this.potions = potions;
		Log.logInfo(this.toString());
	}

	public String getName() {
		return this.name;
	}

	public List<KitItem> getItems() {
		return this.items;
	}

	public List<KitPotion> getPotions() {
		return this.potions;
	}

	public boolean hasItems() {
		return this.items != null || this.items.size() > 0 ? true : false;
	}

	@Override
	public String toString() {
		String format = null; 
		for(KitItem items : this.getItems()){
			format = items.getMaterial().name().replace(" ", "_");
		}
		String result = format;
		return "Kit [name=" + name + ", items=" + result  + ", potions="
				+ potions + "]";
	}

	public boolean hasName() {
		return this.name != null || this.name != "";
	}

}
