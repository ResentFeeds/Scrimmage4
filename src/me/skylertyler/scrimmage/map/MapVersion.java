package me.skylertyler.scrimmage.map;

public class MapVersion {

	protected int major;
	protected int minor;
	protected int patch;

	public MapVersion(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	// this will return true if its newer and false if its not!
	public boolean isNewerThan(MapVersion version) {
		if (version != null) {
			return this.major == version.major && this.minor <= version.minor
					&& this.patch == version.patch;
		}
		return false;
	}

	public static MapVersion parseVersion(String node) {
		String[] parts = node.split(".");
		if (parts.length >= 3) {
			int major = Integer.parseInt(parts[0]);
			int minor = Integer.parseInt(parts[1]);
			int patch = Integer.parseInt(parts[2]);
			new MapVersion(major, minor, patch);
		}
		return null;
	}

	@Override
	public String toString() {
		return "MapVersion [major=" + major + ", minor=" + minor + ", patch="
				+ patch + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String format() {
		return "MapVersion [major=" + major + ", minor=" + minor + ", patch="
				+ patch +" ]";
	}
}
