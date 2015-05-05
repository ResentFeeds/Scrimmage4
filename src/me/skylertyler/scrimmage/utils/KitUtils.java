package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.exception.KitNotFoundExecption;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.modules.KitModule;

import org.bukkit.entity.Player;

public class KitUtils {
	// name needs to be exactly how the kit 'name' attribute is!
	public static Kit getKit(String id) {
		Kit result = null;
		List<Kit> kits = getKitModule().getKitParser().getKits();
		for (Kit kit : kits) {
			if (kit.getID().equalsIgnoreCase(id)) {
				result = kit;
			}
		}
		return result;
	}

	// getter for the kit module :)
	public static KitModule getKitModule() {
		return (KitModule) Scrimmage.getScrimmageInstance().getLoader().getContainer().getModule(KitModule.class);
	}

	// get all the kit names!
	public static List<String> getKitIDs() {
		List<String> names = new ArrayList<String>();
		for (Kit kit : getKitModule().getKitParser().getKits()) {
			String name = kit.getID();
			names.add(name);
		}
		return names;
	}

	public static void applyKit(String id, Player player) {
		Kit kit = getKit(id);
		if (kit == null) {
			try {
				throw new KitNotFoundExecption(id, player);
			} catch (KitNotFoundExecption e) {
				// nothing
			}
			return;
		}
		// some reason it only gives me one item -_-
		kit.applyKit(player);
	}
}
