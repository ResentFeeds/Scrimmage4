package me.skylertyler.scrimmage.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

public class BookKit {

	private String title;
	private String author;
	private List<String> pages;
	private int slot;

	public BookKit(String title, String author, List<String> pages, int slot) {
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.slot = slot;
	}

	/** apply the book to the player */

	public void apply(Player player) {
		PlayerInventory pi = player.getInventory();
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setAuthor(this.author);
		bm.setTitle(this.title);
		bm.setPages(this.pages);
		book.setItemMeta(bm);
		pi.setItem(this.slot, book);
	}
}
