package me.skylertyler.scrimmage.parsers;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementParser {

	protected Element element;

	public ElementParser(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return this.element;
	}

	public boolean hasNode(Node node) {
		return node != null;
	}
}
