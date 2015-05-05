package me.skylertyler.scrimmage.filter.types;

import org.w3c.dom.Element;

import me.skylertyler.scrimmage.filter.Filter;
import me.skylertyler.scrimmage.filter.FilterState;
import me.skylertyler.scrimmage.team.Team;

public class TeamFilter extends Filter {

	private final Team team;

	public TeamFilter(Element element, String id, Team team) {
		super(element, id);
		this.team = team;
	}

	public Team getTeam() {
		return this.team;
	}

	/** 
	 *  this will have a team paremeter "team"
	 */
	@Override
	public FilterState evaluate(Object... objects) {
		for (Object all : objects) {
			if (!(all instanceof Team))
				return null;
			Team team = (Team) all;
			/** check if they match */
			if (team.equals(this.team)) {
				return FilterState.ALLOW;
			}
		}
		return FilterState.DENY;
	} 
}
