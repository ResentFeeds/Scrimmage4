package me.skylertyler.scrimmage.api;

public class PlayerAPI {

	
	public static OvercastPlayer getPlayer(String user){
		OvercastPlayer player = new OvercastPlayer(user);
		return player;
	}
}
