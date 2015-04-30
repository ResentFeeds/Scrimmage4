package me.skylertyler.scrimmage.exception;

import javax.annotation.Nullable;

import org.w3c.dom.Element;

import com.google.common.base.Optional;

public class XMLLoadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6132813527806080173L;
	private Optional<Element> element;

	public XMLLoadException(@Nullable Element element, String message) {
		super(message);
		this.element = Optional.fromNullable(element);
	}

	/** check if the element is present and not null */
	public boolean hasElement() {
		return this.element.isPresent() && this.element.get() != null;
	}

}
