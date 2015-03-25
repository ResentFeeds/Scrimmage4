package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.maxheight.MaxHeight;
import me.skylertyler.scrimmage.utils.NumberUtils;

import org.bukkit.event.HandlerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@ModuleInfo(name="maxheight", module = MaxBuildHeightModule.class)
public class MaxBuildHeightModule extends Module {

	private MaxHeight maxheight;

	public MaxBuildHeightModule() {
		this.maxheight = null;
	}

	public MaxBuildHeightModule(MaxHeight maxheight) {
		this.maxheight = maxheight;
	}

	@Override
	public Module parse(Document doc) {
		MaxBuildHeightModule max = null;
		Element root = doc.getDocumentElement();
		Node node = root.getElementsByTagName("maxbuildheight").item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String height = element.getTextContent();
				int number = NumberUtils.parseInteger(height);
				max = new MaxBuildHeightModule(new MaxHeight(number));
			}
		} else {
			max = new MaxBuildHeightModule(new MaxHeight(256));
		}

		return max;
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public MaxHeight getMaxHeight() {
		return this.maxheight;
	}
}
