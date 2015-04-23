package me.skylertyler.scrimmage.kit;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import com.google.common.base.Optional;

import me.skylertyler.scrimmage.utils.KitUtils;
import me.skylertyler.scrimmage.utils.Log;

public class Kit {
	private final String name;
	private final List<ItemKit> items;

	// need to implement a parser for all the other kits!
	private final Optional<ArmorKit> armor;

	// these work
	private final HealthKit health;
	private final HungerKit hunger;

	// need to test -_-
	private Optional<KnockbackReductionKit> reduction;
	private Optional<String> parent;
	private boolean forced;
	private Optional<List<PotionKit>> potions;

	// will add armor kit , potion kit etc. :) and change all of the regions,
	// spawns, kits to use the id attribute instead of the name */
	public Kit(String name, @Nullable List<ItemKit> items,
			@Nullable ArmorKit armor, @Nullable List<PotionKit> potions,
			@Nullable KnockbackReductionKit reduction, @Nullable String parent,
			boolean forced) {
		this.name = name;
		this.items = items;
		this.armor = Optional.fromNullable(armor);
		this.potions = Optional.fromNullable(potions);
		this.health = new HealthKit(20, 20);
		this.hunger = new HungerKit(20, 5.0f);
		this.reduction = Optional.fromNullable(reduction);
		this.parent = Optional.fromNullable(parent);
		this.forced = forced;
		// TODO KnockbackReductionKit,PotionKit, JumpKit etc :)
		Log.logInfo(toString());
	}

	public boolean isForced() {
		return this.forced;
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

	public Optional<ArmorKit> getArmor() {
		return this.armor;
	}

	public boolean hasArmor() {
		return this.getArmor() != null;
	}

	public boolean hasPotions() {
		return this.potions.isPresent() && this.potions.get().size() > 0;
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

		String reduction = "DEFAULT REDUCTION";
		if (this.reduction.isPresent()) {
			reduction = this.reduction.get().getReduction() + "";
		}
		return "Kit [name=" + name + ", items=" + result + "reduction="
				+ reduction + "]";
	}

	public HungerKit getHunger() {
		return this.hunger;
	}

	public HealthKit getHealth() {
		return this.health;
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
			if (this.armor.isPresent()) {
				ArmorKit armor = this.armor.get();
				armor.apply(player);
			}
 
			/** if the kit has an "potion" tag/element do this */
			if (hasPotions()) {
				for (PotionKit potions : this.potions.get()) {
					potions.apply(player);
				}
			}

			// TODO make kits check if they are being forced if they are it will
			// get rid of everything and put the kit that is being forced */
			/** apply the parent only if present */
			if (this.parent.isPresent()) {
				KitUtils.applyKit(this.parent.get(), player);
			}

			/** only apply if present */
			if (this.reduction.isPresent()) {
				KnockbackReductionKit reductionkit = this.reduction.get();
				reductionkit.apply(player);
			}
		}
	}
}
