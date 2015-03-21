package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.contributor.Contributor;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContributorCommand implements CommandExecutor {

	protected Match match;

	public ContributorCommand(Match match) {
		this.match = match;
	}

	// this is just for now 
	// GRAY > *  WHITE > contributor (contribution) if it has a contribution!
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			MapInfo info = map.getInfo();
			if (label.equalsIgnoreCase("contributors")) {
				if (match != null) {
					if (info.getContributors() != null) {
						for (Contributor contribs : info.getContributors()) {
							String format = null;
							if (contribs.hasContribution()) {
								format = contribs.getContributor() + " ("
										+ contribs.getContribution() + ") ";
							} else {
								format = contribs.getContributor();
							}
							player.sendMessage(ChatColor.GRAY + "* "
									+ ChatColor.RESET + format);
						}
					}
				}
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}

}
