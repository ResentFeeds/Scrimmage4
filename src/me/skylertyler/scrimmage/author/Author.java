package me.skylertyler.scrimmage.author;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Author {
	private Set<UUID> authors = new HashSet<>();
	private final String contribution;
	private final UUID uuid; 

	public Author(UUID uuid, String contribution) {
		this.uuid = uuid;
		this.contribution = contribution;
	}
	 

	public Author(UUID uuid) {
		this(uuid, null);
	}

	public String getContribution() {
		return this.contribution;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public boolean hasContribution() {
		return this.getContribution() != null;
	}

	// used in author / contributor map perks :)
	public boolean matchUUID(Player player) {
		if (player.getUniqueId().equals(getUUID())) {
			return true;
		}
		return false;
	}

	public boolean addUUID() {
		return this.authors.add(this.uuid);
	}

	public Set<UUID> getAuthorUUIDS() {
		return this.authors;
	}

}
