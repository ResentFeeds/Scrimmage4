package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.TeamModule;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamType;

public class TeamUtils {

	public static List<Team> particpating = new ArrayList<>();

	// get the teamtype from and ID example: below
	// TeamType type = getTypeByID(0);
	// it would turn out to be Observers!
	public static TeamType getTypebyID(int id) {
		for (TeamType type : TeamType.values()) {
			if (type.getID() == id) {
				return type;
			}
		}
		return null;
	}

	// get the team by the id (used for objectives) within XML CASE-SENSATIVE
	public static Team getTeamByID(String id) {
		Team result = null;
		for (Team team : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeams()) {
			if (team.getId().equals(id)) {
				result = team;
			}
		}
		return result;
	}

	// get a team by its full name or what ever it starts with :)
	public static Team getTeamByName(String name) {
		Team result = null;
		for (Team team : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeams()) {
			String teamName = team.getName();
			if (teamName.equalsIgnoreCase(name) || teamName.startsWith(name)) {
				result = team;
			}
		}
		return result;
	}

	// just getting a team by their teamtype parameter (note this will get only
	// one team) because the method returns a team not a Collection or a List of
	// teams !
	public static Team getTeamByType(TeamType type) {
		Team result = null;
		for (Team team : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeams()) {
			if (team.getType().equals(type)) {
				result = team;
			}
		}
		return result;
	}

	// gets all the particpating teams
	public static List<Team> getParticpatingTeams() {
		for (Team team : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeams()) {
			if (team.getType().equals(TeamType.Participating)) {
				particpating.add(team);
			}
		}
		return particpating;
	}

	// gets the observers!
	public static Team getObservers() {
		Team result = null;
		for (Team team : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeams()) {
			if (team.getType().equals(TeamType.Observing)) {
				result = team;
			}
		}
		return result;
	}

	public static Team getParticipatingTeamByID(String id) {
		Team result = null;
		for (Team team : getParticpatingTeams()) {
			if (team.getId().equalsIgnoreCase(id)) {
				result = team;
			}
		}
		return result;
	}

	public static TeamModule getTeamModule() {
		TeamModule teamMod = (TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class);
		return teamMod;
	}

	public static Team matchTeam(String team) {
		Team result = null;
		String lowerName = team.toLowerCase();
		int delta = Integer.MAX_VALUE;
		for (Team player : getParticpatingTeams()) {
			if (player.getName().toLowerCase().startsWith(lowerName)) {
				int curDelta = player.getName().length() - lowerName.length();
				if (curDelta < delta) {
					result = player;
					delta = curDelta;
				}
				if (curDelta == 0)
					break;
			}
		}
		return result;
	}
}
