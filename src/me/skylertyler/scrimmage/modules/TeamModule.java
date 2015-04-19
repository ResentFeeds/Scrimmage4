package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.team.TeamParser;

import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;

@ModuleInfo(name = "team", desc = "team module", module = TeamModule.class)
public class TeamModule extends Module {

	private TeamParser tp;

	public TeamModule() {
	}

	public TeamModule(TeamParser tp) {
		this.tp = tp;
	}

	@Override
	public Module parse(Document doc) {
		return new TeamModule(new TeamParser(doc.getDocumentElement(), "teams", "team"));
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public TeamParser getTeamParser() {
		return this.tp;
	}
}
