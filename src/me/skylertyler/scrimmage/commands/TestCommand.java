package me.skylertyler.scrimmage.commands;
 
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	private final Match match;

	public TestCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			MapInfo info = map.getInfo(); 
			if (label.equalsIgnoreCase("test")) {
				if (match != null) {
					String name = info.getName();
					player.sendMessage(name);
				}
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}

}
