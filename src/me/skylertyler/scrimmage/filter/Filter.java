package me.skylertyler.scrimmage.filter;

import javax.annotation.Nullable;

import org.w3c.dom.Element;

import com.google.common.base.Optional;

public abstract class Filter {

	private final Optional<Element> element;
	private final String id;

	public Filter(@Nullable Element element, String id) {
		this.element = Optional.fromNullable(element);
		this.id = id;
	}

	/** optional to have the id */
	public Optional<Element> getElement() {
		return this.element;
	}

	/** check if they got an element */
	public boolean hasElement() {
		return this.getElement().isPresent() && this.getElement().get() != null;
	}

	public String getID() {
		return this.id;
	}

	public boolean hasID() {
		return this.id != "";
	}

	public abstract FilterState evaluate(final Object... objects);
}
