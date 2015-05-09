package me.skylertyler.scrimmage.kit;


public class DoubleJumpKit {

	private boolean enabled;
	private int power;
	private int chargetime;
	private boolean rechargebeforelanding;

	public DoubleJumpKit(boolean enabled, int power, int chargetime,
			boolean rechargebeforelanding) {
		this.enabled = enabled;
		this.power = power;
		this.chargetime = chargetime;
		this.rechargebeforelanding = rechargebeforelanding;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public int getPower() {
		return this.power;
	}

	public int getRechargeTime() {
		return this.chargetime;
	}

	public boolean isRechargingBeforeLandingEnabled() {
		return this.rechargebeforelanding;
	} 
}
