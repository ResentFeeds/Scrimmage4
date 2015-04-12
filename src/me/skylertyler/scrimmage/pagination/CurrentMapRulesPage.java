package me.skylertyler.scrimmage.pagination;

import org.bukkit.ChatColor;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.rules.Rule;
import me.skylertyler.scrimmage.utils.StringUtils;

public class CurrentMapRulesPage<T> extends PaginatedResult<T> {

	private Map map;

	public CurrentMapRulesPage() {
		this.map = Scrimmage.getScrimmageInstance().getMatch().getMap();
	}

	@Override
	public String formatHeader(int page, int maxPages) {
		ChatColor header = ChatColor.WHITE;
		ChatColor textColor = ChatColor.DARK_AQUA;
		ChatColor highlight = ChatColor.AQUA;
		String message = header + "Rules for " + getMap().getInfo().getName()
				+ textColor + " (" + highlight + page + textColor + " of "
				+ highlight + maxPages + textColor + ")";
		return StringUtils.padMessage(message, ChatColor.STRIKETHROUGH
				+ "-----", highlight, header);
	}

	@Override
	public String format(T entry, int index) {
		Rule rule = getMap().getInfo().getRuleByString((String) entry);
		return (index + 1) + ". " + rule.getRule();
	}

	public Map getMap() {
		return this.map;
	}
}