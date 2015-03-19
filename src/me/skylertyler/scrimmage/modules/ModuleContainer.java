package me.skylertyler.scrimmage.modules;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import me.skylertyler.scrimmage.exception.InvalidModuleException;
import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.bukkit.ChatColor;
import org.w3c.dom.Document;

public class ModuleContainer {
	protected HashMap<Module, ModuleInfo> modules = Maps.newHashMap();

	public ModuleContainer() {
		
	}
	
	
	public void enableModules(Document doc){
		for (ModuleInfo info : ModuleRegistry.getModules())
			try {
				addModule(info, doc);
			} catch (InvalidModuleException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}

		sendModuleMessage(ModuleRegistry.getModules().size());
	}

	public void sendModuleMessage(int size) {
		String format = null;
		if (size != 0) {
			if (size > 1) {
				format = ChatColor.GREEN + "Modules";
			} else {
				format = ChatColor.YELLOW + "Module";
			}
		} else {
			format = ChatColor.RED + "Modules";
		}
		String message = ChatColor.GRAY + "You got " + size + " " + format
				+ " loaded";
		ConsoleUtils.sendConsoleMessage(message);
	}

	public Module getModule(Class<? extends Module> clazz) {
		for (Module module : modules.keySet())
			if (module.getClass().equals(clazz))
				return module;
		return null;
	}

	public Module getModule(String name) {
		for (Module module : modules.keySet()) {
			if (module.getClass().getAnnotation(ModuleInfo.class) != null
					&& module.getClass().getAnnotation(ModuleInfo.class).name()
							.equals(name)) {
				return module;
			}
		}
		return null;
	}

	public void unloadModules() {
		for (Module module : modules.keySet())
			module.unload();
	}

	public void addModule(ModuleInfo info, Document doc)
			throws InstantiationException, IllegalAccessException,
			InvalidModuleException {
		for (Class<? extends Module> dependency : info.requires()) {
			if (!dependency.isAnnotationPresent(ModuleInfo.class))
				throw new InvalidModuleException("modules.Module: "
						+ dependency.getClass().getSimpleName()
						+ " does not have @modules.ModuleInfo tag!");
			addModule(dependency.getAnnotation(ModuleInfo.class), doc);
		}
		if (registered(info))
			return;
		Module module = ((Module) info.module().newInstance()).parse(doc);
		if (module == null)
			return;
		modules.put(module, info);
	}

	private boolean registered(ModuleInfo info) {
		for (Map.Entry<Module, ModuleInfo> entry : modules.entrySet())
			if (entry.getValue().name().equals(info.name()))
				return true;
		return false;
	} 
}
