package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.pagination.CurrentMapRulesPage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class RuleCommand implements CommandExecutor {

	private Match match;
	private CurrentMapRulesPage<String> mapsPage;

	public RuleCommand(Match match) {
		this.match = match;
		this.mapsPage = new CurrentMapRulesPage<String>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			MapInfo info = map.getInfo();
			if (cmd.getName().equalsIgnoreCase("rules")) {
				if (match != null) {
					if (info.hasRules()) {
						if (args.length == 0) {
							this.mapsPage
									.display(player, info.getMapRules(), 1);
						}

						if (args.length > 1) {
							player.sendMessage(RED + "/rules <page> ");
							return false;
						}

						if (args.length == 1) {
							int page = Integer.parseInt(args[0]);
							if (page < 1 || page > this.mapsPage.getMaxPages()) {
								String format = null;
								if (page < 1) {
									format = RED + "the page " + DARK_RED
											+ page + RED
											+ " needs to be at least "
											+ DARK_RED + "" + 1;
								} else {
									if (page > this.mapsPage.getMaxPages()) {
										format = RED + "the page " + DARK_RED
												+ page + RED
												+ " needs to be at most "
												+ DARK_RED
												+ this.mapsPage.getMaxPages();
									}
								}
								String result = format;
								player.sendMessage(result);
								return false;
							}
							this.mapsPage.display(player, info.getMapRules(),
									page);
						}
					} else {
						player.sendMessage(RED + "No rules for this map!");
					}
				}
			}
		}
		return true;
	}

	public Match getMatch() {
		return this.match;
	}
}
