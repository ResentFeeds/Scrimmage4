package me.skylertyler.scrimmage.api;

import com.google.common.base.Preconditions;

public class OvercastPlayer {

   String user;
   String profileURL;

	public OvercastPlayer(String user){
		Preconditions.checkNotNull(user, "the user can't be null");
		this.user = user;
		this.profileURL = OvercastUtil.getOvercastURL() + this.user;
	}
	
	
	public String getProfileURL(){
		return this.profileURL;
	}
}
