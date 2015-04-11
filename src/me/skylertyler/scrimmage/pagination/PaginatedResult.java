package me.skylertyler.scrimmage.pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

/**
 * Commands that wish to display a paginated list of results can use this class
 * to do the actual pagination, giving a list of items, a page number, and basic
 * formatting information.
 */
public abstract class PaginatedResult<T> {
	protected final int resultsPerPage;

	public PaginatedResult() {
		this(6);
	}

	public PaginatedResult(int resultsPerPage) {
		assert resultsPerPage > 0;
		this.resultsPerPage = resultsPerPage;
	}

	public void display(CommandSender sender, Collection<? extends T> results,
			int page) throws CommandException {
		this.display(sender, new ArrayList<T>(results), page);
	}

	public void display(CommandSender sender, List<? extends T> results,
			int page) throws CommandException {
		if (results.size() == 0)
			throw new CommandException("No results match!");

		int maxPages = results.size() / this.resultsPerPage + 1;
		if (page <= 0 || page > maxPages)
			throw new CommandException("Unknown page selected! " + maxPages
					+ " total pages.");

		sender.sendMessage(this.formatHeader(page, maxPages));

		for (int i = this.resultsPerPage * (page - 1); i < this.resultsPerPage
				* page
				&& i < results.size(); i++) {
			sender.sendMessage(this.format(results.get(i), i));
		}
	}

	public abstract String formatHeader(int page, int maxPages);

	public abstract String format(T entry, int index);
}
