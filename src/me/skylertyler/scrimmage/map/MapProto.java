package me.skylertyler.scrimmage.map;

public class MapProto {

	protected int major;
	protected int minor;
	protected int patch;

	public MapProto(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public String[] formatMapProto(String version) {
		String[] split = version.split(".");
		for (int i = 0; i < 3; i++) {
			this.major = Integer.parseInt(split[0]);
			this.minor = Integer.parseInt(split[1]);
			this.patch = Integer.parseInt(split[2]);
		}
		return split;
	}
}
