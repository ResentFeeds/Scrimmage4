package me.skylertyler.scrimmage.listeners;

import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.event.ChannelChangeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.ChatColor.*;

public class ChannelListener implements Listener {

	public ChannelListener() {

	}

	@EventHandler
	public void onChannelChange(ChannelChangeEvent event) {
		Player player = event.getPlayer();
		boolean hasOld = event.hasOldChannel();
		Channel nchannel = event.getNewChannel();
		if (hasOld) {
			Channel channel = event.getOldChannel();
			boolean match = channel == nchannel;
			if (match) {
				event.setCancelled(true, RED + "You are already in the "
						+ nchannel.getName());
				return;
			}
			channel.removePlayer(player);
			nchannel.addPlayer(player);
			event.setMessage("You left the " + channel.getName() + RESET
					+ " and joined the " + nchannel.getName());
			player.sendMessage(event.getMessage());
		} else {
			nchannel.addPlayer(player);
			event.setMessage("You joined the " + nchannel.getName());
			player.sendMessage(event.getMessage());
		}
	}
}
