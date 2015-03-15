package me.skylertyler.scrimmage.modules;

import org.bukkit.event.Listener;
import org.w3c.dom.Document;

//Tomorrow work on fixing the InfoModule.class and work on lowering down the exceptions list on all try{
// and catches!
public abstract class Module implements Listener {

	public abstract void unload();

	public abstract void load(Document doc); 
}
