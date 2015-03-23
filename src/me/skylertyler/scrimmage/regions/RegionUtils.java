package me.skylertyler.scrimmage.regions;

import java.util.HashMap;
import java.util.Map.Entry;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.regions.types.EmptyRegion;
import me.skylertyler.scrimmage.regions.types.PointRegion;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.NumberUtils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RegionUtils {

	public static HashMap<String, Region> regions = new HashMap<String, Region>();

	public static HashMap<String, Region> getRegions() {
		return regions;
	}

	public static HashMap<String, Region> parseRegions(Element root,
			String regionTag) {
		Node node = root.getElementsByTagName(regionTag).item(0);
		if (node != null) {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element nodeElement = (Element) node;
				// put filters' 
				NodeList allRegions = nodeElement.getChildNodes();
				for (int i = 0; i < allRegions.getLength(); i++) {
					Node regionNode = allRegions.item(i);
					if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
						Element regionElement = (Element) regionNode;
						if (isRegionTag(regionNode)) {
							Region region = parseRegion(regionNode);

							regions.put(regionElement.getAttribute("name"),
									region);
						}
					}
				}
			}
		}
		return regions;
	}

	private static Region parseRegion(Node regionNode) {
		if (isRegionTag(regionNode)) {
			/*
			 * if (regionNode.getNodeName().equals("cylinder")) return
			 * RegionUtils.parseCylinder(tag, inverted); else if
			 * (regionNode.getNodeName().equals("cuboid")) return
			 * RegionUtils.parseCuboid(tag, inverted); else if
			 * (regionNode.getNodeName().equals("rectangle")) return
			 * RegionUtils.parseRectangle(g); else if
			 * (regionNode.getNodeName().equals("circle")) return
			 * RegionUtils.parseCircle(tag, inverted); else if
			 * (regionNode.getNodeNae().equals("sphere")) return
			 * RegionUtils.parseSphere(tag, inverted); else if
			 * (regionNode.getNodeName().equals("union")) return
			 * RegionUtils.parseUnion(tag, inverted); else if
			 * (regionNode.getNodeName().equals("complement")) return
			 * RegionUtils.parseComplement(tag, inverted); else if
			 * (regionNode.getNodeName().equals("negative")) return
			 * RegionUtils.parseNegative(tag); else if
			 * (regionNode.getNodeName().equals("point")) return
			 * RegionUtils.parsePoint(tag, inverted);
			 */
			if (regionNode.getNodeName().equals("block")) {
				Log.logInfo(regionNode.getTextContent());
				return parseBlock(regionNode);
			} else if (regionNode.getNodeName().equals("empty")) {
				return parseEmpty(regionNode);
			} else if (regionNode.getNodeName().equals("point")) {
				return parsePoint(regionNode);
			}
		} else {
			Log.logWarning("there is no region called "
					+ regionNode.getNodeName());
		}
		return null;
	}

	private static PointRegion parsePoint(Node regionNode) {
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element pointElement = (Element) regionNode;
			String[] points = pointElement.getTextContent().split(",");
			double x, y, z;
			x = NumberUtils.parseDouble(points[0]);
			y = NumberUtils.parseDouble(points[1]);
			z = NumberUtils.parseDouble(points[2]);
			InfoModule module = (InfoModule) Scrimmage.getScrimmageInstance()
					.getLoader().getModuleContainer()
					.getModule(InfoModule.class);
			String name = "match-" + module.getInfo().getName();
			Location loc = new Location(Bukkit.getWorld(name), x, y, z);
			Vector vec = loc.toVector();
			if (pointElement.hasAttribute("name")) {
				String regionName = pointElement.getAttribute("name");
				return new PointRegion(vec, regionName);
			} else {
				return new PointRegion(vec, "");
			}
		}
		return null;
	}

	private static EmptyRegion parseEmpty(Node regionNode) {
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element emptyElement = (Element) regionNode;
			if (emptyElement.hasAttribute("name")) {
				String name = emptyElement.getAttribute("name");
				return new EmptyRegion(name);
			} else {
				return new EmptyRegion("");
			}
		}
		return null;
	}

	private static Region parseBlock(Node regionNode) {
		String[] parts = regionNode.getTextContent().split(",");
		double x = NumberUtils.parseDouble(parts[0]);
		double y = NumberUtils.parseDouble(parts[1]);
		double z = NumberUtils.parseDouble(parts[2]);
		InfoModule module = (InfoModule) Scrimmage.getScrimmageInstance()
				.getLoader().getModuleContainer().getModule(InfoModule.class);
		String name = "match-" + module.getInfo().getName();
		Location loc = new Location(Bukkit.getWorld(name), x, y, z);
		Vector vec = loc.toVector();
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) regionNode;
			if (e.hasAttribute("name")) {
				String attributeName = e.getAttribute("name");
				return new BlockRegion(vec, attributeName);
			} else {
				return new BlockRegion(vec);
			}
		}
		return null;
	}

	public static boolean isRegionTag(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			switch (node.getNodeName()) {
			case "block":
			case "empty":
			case "point":
				return true;
			default:
				return false;
			}
		}
		return false;
	}

	// Not tested
	public static boolean containsRegion(Region region) {
		for (Entry<String, Region> regions : getRegions().entrySet()) {
			if (regions.getValue() == region) {
				return true;
			}
		}

		return false;
	}

	// get a region by its name attribute!
	public static Region getRegionFromString(String input) {
		Region region = null;
		for (Entry<String, Region> regions : getRegions().entrySet()) {
			if (regions.getKey().equalsIgnoreCase(input)) {
				region = regions.getValue();
			}
		}
		return region;
	}
}