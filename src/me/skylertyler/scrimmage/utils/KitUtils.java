package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.exception.KitNotFoundExecption;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.modules.KitModule;

import org.bukkit.entity.Player;

public class KitUtils {

	// TODO add KitPotion
	// TODO fix only doing one item as (stated below)

	// name needs to be exactly how the kit 'name' attribute is!
	public static Kit getKitByName(String name) {
		Kit result = null;
		List<Kit> kits = getKitModule().getKits();
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
		for (Kit kit : getKitModule().getKits()) {
			String name = kit.getName();
			names.add(name);
		}
		return names;
	}

	public static void applyKit(String name, Player player) {
		Kit kit = getKitByName(name);
		if (kit == null) {
			try {
				throw new KitNotFoundExecption(name, player);
			} catch (KitNotFoundExecption e) {
				// nothing
			}
			return;
		}
		// some reason it only gives me one item -_-
		kit.applyKit(player);
	}
}
