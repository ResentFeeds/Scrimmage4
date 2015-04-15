package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.exception.NoSpawnsException;
import me.skylertyler.scrimmage.spawn.SpawnParser;
import me.skylertyler.scrimmage.utils.Log;

import org.bukkit.event.HandlerList;
import org.w3c.dom.Document; 

@ModuleInfo(name = "spawn", module = SpawnModule.class, requires = TeamModule.class)
public class SpawnModule extends Module {

	private SpawnParser sp;

	public SpawnModule() {
		this.sp = null;
	}

	public SpawnModule(SpawnParser sp) {
		this.sp = sp;

		int size = this.getSpawnParser().getSpawns().size();

		/** if the size is 0 it will throw the exception below */
		if (size == 0) {
			try {
				throw new NoSpawnsException(size);
			} catch (NoSpawnsException e) {
				// log a warning if the are no spawns!
				Log.logWarning(e.getMessage());
			}
		}
	}

	@Override
	public Module parse(Document doc) {
		return new SpawnModule(new SpawnParser(doc.getDocumentElement(),
				"spawns", "spawn", "default"));
	}

	public SpawnParser getSpawnParser() {
		return this.sp;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}
}
