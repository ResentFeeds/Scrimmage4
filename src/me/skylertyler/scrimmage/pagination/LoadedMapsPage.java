package me.skylertyler.scrimmage.pagination;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.utils.StringUtils;

import org.bukkit.ChatColor;

public class LoadedMapsPage<T> extends PaginatedResult<T> {

	public LoadedMapsPage() {
		super();
	}

	@Override
	public String formatHeader(int page, int maxPages) {
		ChatColor loadedmaps = ChatColor.WHITE;
		ChatColor textColor = ChatColor.DARK_AQUA;
		ChatColor highlight = ChatColor.AQUA;

		String message = loadedmaps + "Loaded Maps" + textColor + " ("
				+ highlight + page + textColor + " of " + highlight + maxPages
				+ textColor + ")";
		return StringUtils.padMessage(message, ChatColor.STRIKETHROUGH
				+ "-----", highlight, loadedmaps);
	}

	@Override
	public String format(T entry, int index) {
		Map map = Scrimmage.getScrimmageInstance().getLoader()
				.getMap((String) entry);
		return (index + 1) + ". " + map.getMapDescription();
	}
}
