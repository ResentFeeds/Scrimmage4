package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.exception.KitNotFoundExecption;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.kit.KitItem;
import me.skylertyler.scrimmage.modules.KitModule;

public class KitUtils {

	public static List<Kit> kits = new ArrayList<>();
	//TODO add KitPotion
	//TODO fix only doing one item as (stated below)
	
	// name needs to be exactly how the kit 'name' attribute is!
	public static Kit getKitByName(String name) {
		Kit result = null;
		List<Kit> kits = getKits();
		for (Kit kit : kits) {
			if (kit != null) {
				if (kit.getName().equals(name)) {
					result = kit;
				}
			}
		}
		return result;
	}

	// getter for the kit module :)
	public static KitModule getKitModule() {
		return (KitModule) Scrimmage.getScrimmageInstance().getLoader()
				.getContainer().getModule(KitModule.class);
	}

	// get all the kit names!
	public static List<String> getKitNames() {
		List<String> names = new ArrayList<String>();
		for (Kit kit : getKits()) {
			if (kit.hasName()) {
				String name = kit.getName();
				names.add(name);
			}
		}
		return names;
	}

	public static void applyKit(String name, Player player) {
		PlayerInventory pi = player.getInventory();
		Kit kit = getKitByName(name);
		if (kit == null) {
			try {
				throw new KitNotFoundExecption(name, player);
			} catch (KitNotFoundExecption e) {
				 // nothing
			}
			return;
		}

		// TODO fix only doing one item -_- 
		KitItem all = null;
		for (KitItem items : kit.getItems()) {
			all = items;
		}

		ItemStack stack = new ItemStack(all.getMaterial(), all.getAmount());
		int slot = all.getSlot();
		pi.setItem(slot, stack);
	}

	// weird way of adding kits -_- but works
	public static List<Kit> getKits() {
		Kit result = null;
		for (Kit kit : getKitModule().getKits()) {
			result = kit;
		}
		kits.add(result);
		return kits;
	}
}
