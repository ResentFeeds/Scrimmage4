package me.skylertyler.scrimmage.modules;

import org.bukkit.event.Listener;
import org.w3c.dom.Document;

/**
 * 
 * @author SkipperGuy12 
 * just used them in this
 * 
 */
public abstract class Module implements Listener {
	public abstract Module parse(Document doc);

	public abstract void unload();
}
