package me.skylertyler.scrimmage.contributor;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Contributor {

	protected String contribution;
	protected String contributor;

	public Contributor(String contribution, String contributor) {
		this.contribution = contribution;
		this.contributor = contributor;
	}

	public Contributor(String contributor) {
		this.contributor = contributor;
		this.contribution = null;
	}

	public String getContributor() {
		return this.contributor;
	}

	public String getContribution() {
		return this.contribution;
	}

	public boolean hasContribution() {
		return this.contribution != null;
	}

	public static Contributor parseContributor(Element element) {
		Node node = element.getElementsByTagName("contributors").item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element nodeElement = (Element) node;
			NodeList list = nodeElement.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node contributor = list.item(i);
				if (contributor.getNodeType() == Node.ELEMENT_NODE) {
					Element contributorElelement = (Element) contributor;
					String text = contributorElelement.getTextContent();
					if (contributorElelement.hasAttribute("contribution")) {
						return new Contributor(
								contributorElelement
										.getAttribute("contribution"),
								text);
					} else {
						return new Contributor(text);
					}
				}
			}
		}
		return null;
	}
}
