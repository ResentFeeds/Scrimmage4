package me.skylertyler.scrimmage.modules;

import java.util.ArrayList;
import java.util.Collection;

import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.bukkit.ChatColor;
import org.w3c.dom.Document;

public class ModuleFactory {

	protected Collection<Class<? extends Module>> globalModules = new ArrayList<Class<? extends Module>>();
	protected ModuleInfo info;

	public ModuleFactory() {

	}

	public void loadModules(Document doc) throws ModuleLoadException, Throwable {
		int size = getModules().size();
		for (Class<? extends Module> modules : getModules()) {
			this.info = new ModuleInfo(modules);
			this.info.getModuleClass().newInstance().load(doc);
		}

		sendModuleMessage(size);
	}

	public Collection<Class<? extends Module>> getModules() {
		return globalModules;
	}

	public Module getModuleByString(String name) throws InstantiationException,
			IllegalAccessException {
		Module module = null;
		for (Class<? extends Module> modules : getModules()) {
			if (modules.isAnnotationPresent(ModuleDescription.class)
					&& modules != null) {
				ModuleDescription description = modules
						.getAnnotation(ModuleDescription.class);
				if (description.name().equals(name)) {
					module = modules.newInstance();
				} else {
					ConsoleUtils
							.sendConsoleMessage("there is no module by the name of "
									+ name);
				}
			}
		}
		return module;
	}

	public Module getModule(Class<? extends Module> input)
			throws InstantiationException, IllegalAccessException {
		for (Class<? extends Module> modules : this.getModules()) {
			if (modules.getClass() == input.getClass()) {
				return modules.newInstance();
			}
		}
		return null;
	}

	public ModuleInfo getModuleInfo() {
		return this.info;
	}

	public void sendModuleMessage(int amount) {
		String format = null;
		if (amount != 0) {
			if (amount > 1) {
				format = ChatColor.GREEN + "Modules";
			} else {
				format = ChatColor.YELLOW + "Module";
			}
		} else {
			format = ChatColor.RED + "None";
		}
		String message = ChatColor.GRAY + "You got " + amount + " " + format
				+ " loaded";
		ConsoleUtils.sendConsoleMessage(message);
	}

	public void registerModule(Class<? extends Module> clazz) {
		globalModules.add(clazz);
	}

	public void unloadModules() throws InstantiationException,
			IllegalAccessException {
		for (Class<? extends Module> modules : this.getModules()) {
			if (modules != null) {
				modules.newInstance().unload();
			}
		}
	}

}
