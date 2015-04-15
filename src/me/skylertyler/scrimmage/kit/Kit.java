package me.skylertyler.scrimmage.kit;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import me.skylertyler.scrimmage.utils.Log;

public class Kit{
	private final String name;
	private final List<ItemKit> items;

	// need to implement a parser for all the other kits!
	private final ArmorKit armor;

	// these work
	private final HealthKit health;
	private final HungerKit hunger;
	
	// need to test -_-
	private KnockbackReductionKit reduction;

	// fix
	// will add armor kit , potion kit etc. :)
	public Kit(String name, @Nullable List<ItemKit> items,
			@Nullable ArmorKit armor, @Nullable KnockbackReductionKit reduction) {
		this.name = name;
		this.items = items;
		this.armor = armor;
		this.health = new HealthKit(20, 20);
		this.hunger = new HungerKit(20, 5.0f);

		if (hasReductionKit()) {
			this.reduction = reduction;
		} else {
			this.reduction = new KnockbackReductionKit(0.2f);
		}

		// TODO KnockbackReductionKit,PotionKit, JumpKit etc :)
		Log.logInfo(toString());
	}

	public String getName() {
		return this.name;
	}

	public boolean hasName() {
		return this.getName() != null;
	}

	public List<ItemKit> getItems() {
		return this.items;
	}

	public boolean hasItems() {
		return this.getItems() != null || this.getItems().size() > 0;
	}

	public ArmorKit getArmor() {
		return this.armor;
	}

	public boolean hasArmor() {
		return this.getArmor() != null;
	}

	@Override
	public String toString() {
		String result = null;
		for (ItemKit items : this.getItems()) {
			result = items.getStack().getType().name().replace("_", " ")
					.toUpperCase()
					+ " "
					+ items.getLore()
					+ " "
					+ items.getName()
					+ " "
					+ items.getSlot() + " ";
		}
		return "Kit [name=" + name + ", items=" + result + "]";
	}

	public HungerKit getHunger() {
		return this.hunger;
	}

	public HealthKit getHealth() {
		return this.health;
	}

	public boolean hasReductionKit() {
		return getReductionKit() != null;
	}

	public KnockbackReductionKit getReductionKit() {
		return this.reduction;
	}

	public void applyKit(Player player) {
		/** apply the itemKit */
		for (ItemKit items : getItems()) {
			if (items != null) {
				items.apply(player);
			}
			/** apply the health kit */
			HealthKit health = this.getHealth();
			health.apply(player);

			/** apply the hunger kit */
			HungerKit hunger = this.getHunger();
			hunger.apply(player);

			/** if the kit has armor do this below :) */
			if (hasArmor()) {
				ArmorKit armor = this.getArmor();
				armor.apply(player);
			}

			KnockbackReductionKit reduction = this.getReductionKit();
			reduction.apply(player);
		}
	}  
}
