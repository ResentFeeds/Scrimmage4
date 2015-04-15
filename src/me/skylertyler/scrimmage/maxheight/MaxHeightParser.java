package me.skylertyler.scrimmage.maxheight;

import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.NumberUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MaxHeightParser extends ElementParser {

	private MaxHeight maxheight;

	public MaxHeightParser(Element element, String maxheight) {
		super(element);
		Node node = element.getElementsByTagName(maxheight).item(0);

		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element maxHeightElement = (Element) node;
				int height = NumberUtils.parseInteger(maxHeightElement
						.getTextContent());
				this.maxheight = new MaxHeight(height);
			}
		} else {
			this.maxheight = new MaxHeight(256);
		}
	}

	public MaxHeight getMaxHeight() {
		return this.maxheight;
	}
}
