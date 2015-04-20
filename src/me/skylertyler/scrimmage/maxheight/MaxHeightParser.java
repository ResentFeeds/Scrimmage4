package me.skylertyler.scrimmage.maxheight;

import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.NumberUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MaxHeightParser extends ElementParser {

	private MaxHeight maxheight;
	private String mh;

	public MaxHeightParser(Element element, String maxheight) {
		super(element);
		this.mh = maxheight;
		/**
		 * XML Parsing
		 */
		Node node = getElement().getElementsByTagName(this.mh).item(0);

		/** if it has the max height node */
		if (hasNode(node)) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element maxHeightElement = (Element) node;
				int height = NumberUtils.parseInteger(maxHeightElement
						.getTextContent());
				this.maxheight = new MaxHeight(height);
			}
		} else {
			/** else do this if the maxbuildheight element is null */
			this.maxheight = new MaxHeight(255);
		}
	}

	public MaxHeight getMaxHeight() {
		return this.maxheight;
	}
}
