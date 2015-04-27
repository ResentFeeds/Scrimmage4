package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.channels.TeamChannel;
import me.skylertyler.scrimmage.event.ChannelChangeEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.ChannelUtils;
import me.skylertyler.scrimmage.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCommand implements CommandExecutor {

	private Match match;

	public TCommand() {
		this.match = Scrimmage.getScrimmageInstance().getMatch();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("t")) {
				if (args.length == 0) {
					Channel channel = ChannelUtils.getChannel(player);
					ChannelChangeEvent event = new ChannelChangeEvent(player,
							channel, ChannelUtils.getTeamChannel());
					this.match.getPluginManager().callEvent(event);
				}

				Team team = this.match.getTeamHandler().teamForPlayer(player);

				TeamChannel teamChannel = ChannelUtils.getTeamChannel();

				/** its deprecated because of the Bukkit.getPlayer(String goes here) method */
				if (args.length >= 1) {
					for (String member : team.getMembers()) {
						Player teamMate = Bukkit.getPlayer(member);
						teamMate.sendMessage(teamChannel.format(team, player,
								MessageUtils.broadcast(args)));
					}
					return false;
				}
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to join the Team Channel");
		}
		return false;
	}
}