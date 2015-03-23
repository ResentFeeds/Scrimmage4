package me.skylertyler.scrimmage.match;

import org.bukkit.ChatColor;

public enum MatchState {

	Idle(ChatColor.GRAY), Starting(ChatColor.GREEN), Running(ChatColor.YELLOW), Finished(ChatColor.DARK_RED), Cycling(ChatColor.AQUA);

	protected ChatColor color;
	
    MatchState(ChatColor color) {
    	this.color = color;
	}
    
    
    public ChatColor getColor(){
    	return this.color;
    }
    
    
    
    @Override
    public String toString() {
    	return getColor() + this.name();
    }
}
