package me.skylertyler.scrimmage.pagination;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.utils.StringUtils;

import org.bukkit.ChatColor;

//TODO work on this !
public class RotationMapsPage<T> extends PaginatedResult<T> {

	public RotationMapsPage() {
		super();
	}

	@Override
	public String formatHeader(int page, int maxPages) {
		ChatColor rotationMaps = ChatColor.WHITE;
		ChatColor textColor = ChatColor.DARK_AQUA;
		ChatColor highlight = ChatColor.AQUA;

		String message = rotationMaps + "Rotation" + textColor + " ("
				+ highlight + page + textColor + " of " + highlight + maxPages
				+ textColor + ")";
		return StringUtils.padMessage(message, ChatColor.STRIKETHROUGH
				+ "-----", highlight, rotationMaps);
	}

	@Override
	public String format(T entry, int index) {
		Map map = Scrimmage.getScrimmageInstance().getLoader().getMap((String) entry);
		return (index + 1) + ". " + map.getMapDescription();
	}
}