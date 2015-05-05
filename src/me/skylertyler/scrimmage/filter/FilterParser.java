package me.skylertyler.scrimmage.filter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import me.skylertyler.scrimmage.filter.types.TeamFilter;
import me.skylertyler.scrimmage.parsers.ElementParser;
import me.skylertyler.scrimmage.utils.TeamUtils;

public class FilterParser extends ElementParser {

	private final String filtersTag;
	private List<Filter> filters;

	public FilterParser(Element root, String filtersTag) {
		super(root);
		/** the filters */
		this.filters = new ArrayList<>();
		/** the filters tag */
		this.filtersTag = filtersTag;

		/**
		 * XML PARSING
		 */

		Node node = root.getElementsByTagName(this.filtersTag).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList childElements = element.getChildNodes();
				for (int i = 0; i < childElements.getLength(); i++) {
					Node child = childElements.item(i);
					if (child.getNodeType() == Node.ELEMENT_NODE) {
						Element childElement = (Element) child;
						switch (childElement.getTagName()) {
						case "team":
							this.filters.add(new TeamFilter(childElement,childElement.getAttribute("id"),TeamUtils.getParticipatingTeamByID(childElement.getTextContent())));
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}

	public List<Filter> getFilters() {
		return this.filters;
	}

	public boolean hasFilters() {
		return this.getFilters().size() > 0;
	}
}
