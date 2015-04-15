package me.skylertyler.scrimmage.parsers;

import org.w3c.dom.Element;

public class ElementParser {
	
	private Element element;

	public ElementParser(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return this.element;
	}
}
