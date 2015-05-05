package me.skylertyler.scrimmage.kit;

import java.util.List;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.utils.KitUtils;
import me.skylertyler.scrimmage.utils.Log;

import org.bukkit.entity.Player;

import com.google.common.base.Optional;

public class Kit {
	private final String id;
	private final List<ItemKit> items;

	// need to implement a parser for all the other kits!
	private final Optional<ArmorKit> armor;

	// these work
	private final HealthKit health;
	private final HungerKit hunger;
	private Optional<KnockbackReductionKit> reduction;
	private Optional<List<String>> parents;
	private boolean potionparticles;
	private boolean forced;
	private Optional<List<PotionKit>> potions;
	private boolean discardPotionBottles;

	// will add armor kit , potion kit etc. :) and change all of the regions,
	// spawns, kits to use the id attribute instead of the name */
	public Kit(String id, @Nullable List<ItemKit> items,
			@Nullable ArmorKit armor, @Nullable List<PotionKit> potions,
			@Nullable KnockbackReductionKit reduction,
			@Nullable List<String> parents, boolean forced,
			boolean potionparticles, boolean discardPotionBottles) {
		this.id = id;
		this.items = items;
		this.armor = Optional.fromNullable(armor);
		this.potions = Optional.fromNullable(potions);
		this.health = new HealthKit(20, 20);
		this.hunger = new HungerKit(20, 5.0f);
		this.reduction = Optional.fromNullable(reduction);
		this.parents = Optional.fromNullable(parents);
		this.forced = forced;
		this.potionparticles = potionparticles;
		this.discardPotionBottles = discardPotionBottles;
		// TODO KnockbackReductionKit,PotionKit, JumpKit etc :)
		Log.logInfo(toString());
	}

	public boolean isForced() {
		return this.forced;
	}

	public String getID() {
		return this.id;
	}

	public boolean hasID() {
		return this.getID() != null;
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

	public boolean getPotionParticles() {
		return this.potionparticles;
	}

	public boolean hasPotionParticles() {
		// if the potion particles is not equal to false it will be true
		// otherwise it would be false as stated below */
		return this.getPotionParticles() != false ? true : false;
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
		String PARENT = " Parents";
		String parents = "";
		if (this.hasParents()) {
			Optional<List<String>> allParents = this.getParents();
			if (allParents.get().size() == 1) {
				parents += allParents.get().get(0) + PARENT;
			} else {
				parents += allParents.asSet().toString();
			}
		}else{
			parents += "No Parrents";
		}
		return "Kit [id=" + id + ", items=" + result + "reduction=" + reduction
				+ ", parents=" + parents + "]";
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
			/** apply the parents only if present */
			if (hasParents()) {
				Optional<List<String>> parents = this.getParents();
				int size = parents.get().size();
				if (size == 1) {
					KitUtils.applyKit(parents.get().get(0), player);
				} else {
					if (size > 1) {
						for (String kitId : parents.get()) {
							KitUtils.applyKit(kitId, player);
						}
					}
				}
			}

			/** only apply if present */
			if (this.reduction.isPresent()) {
				KnockbackReductionKit reductionkit = this.reduction.get();
				reductionkit.apply(player);
			}
		}
	}

	public Optional<List<String>> getParents() {
		return this.parents;
	}

	/** check if they got the parents */
	public boolean hasParents() {
		return this.getParents().get().size() >= 1
				&& this.getParents().isPresent();
	}

	public boolean getDiscardPotionBottles() {
		return this.discardPotionBottles;
	}

	public boolean isDiscardPotionBottlesEnabled() {
		return this.getDiscardPotionBottles() != false ? true : false;
	}
}
