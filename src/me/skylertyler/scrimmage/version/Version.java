package me.skylertyler.scrimmage.version;

public class Version {

	protected int major;
	protected int minor;
	protected int patch;

	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	// this will return true if its newer and false if its not!
	public boolean isNewerThan(Version version) {
		if (version != null) {
			return this.major == version.major && this.minor <= version.minor
					&& this.patch == version.patch;
		}
		return false;
	}

	public String splitVersion() {
		return this.major + "." + this.minor + "." + this.patch;
	}

	public static Version parseVersion(String node) {
		String[] parts = node.split(".");
		if (parts.length >= 3) {
			int major = Integer.parseInt(parts[0]);
			int minor = Integer.parseInt(parts[1]);
			int patch = Integer.parseInt(parts[2]);
			new Version(major, minor, patch);
		}
		return null;
	}

	@Override
	public String toString() {
		return this.major + "." + this.minor + "." + this.patch;
	}
}
