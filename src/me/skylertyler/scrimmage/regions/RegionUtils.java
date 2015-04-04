package me.skylertyler.scrimmage.regions;

import java.util.HashMap;
import java.util.Map.Entry;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.regions.types.CuboidRegion;
import me.skylertyler.scrimmage.regions.types.CylinderRegion;
import me.skylertyler.scrimmage.regions.types.EmptyRegion;
import me.skylertyler.scrimmage.regions.types.PointRegion;
import me.skylertyler.scrimmage.regions.types.SphereRegion;
import me.skylertyler.scrimmage.utils.LocationUtils;
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
				return parseBlock(regionNode);
			} else if (regionNode.getNodeName().equals("empty")) {
				return parseEmpty(regionNode);
			} else if (regionNode.getNodeName().equals("point")) {
				return parsePoint(regionNode);
			} else if (regionNode.getNodeName().equals("cuboid")) {
				return parseCuboid(regionNode);
			} else if (regionNode.getNodeName().equals("sphere")) {
				return parseSphere(regionNode);
			} else if (regionNode.getNodeName().equals("cylinder")) {
				return parseCyclinder(regionNode);
			}
		} else {
			Log.logWarning("there is no region called "
					+ regionNode.getNodeName());
		}
		return null;
	}

	public static PointRegion parsePoint(Node regionNode) {
		PointRegion point = null;
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element pointElement = (Element) regionNode;
			String[] points = pointElement.getTextContent().split(",");
			double x, y, z;
			x = NumberUtils.parseDouble(points[0]);
			y = NumberUtils.parseDouble(points[1]);
			z = NumberUtils.parseDouble(points[2]);
			InfoModule module = (InfoModule) Scrimmage.getScrimmageInstance()
					.getLoader().getContainer().getModule(InfoModule.class);
			String name = "match-" + module.getInfo().getName();
			Location loc = new Location(Bukkit.getWorld(name), x, y, z);
			Vector vec = loc.toVector();
			if (pointElement.hasAttribute("name")) {
				String regionName = pointElement.getAttribute("name");
				point = new PointRegion(vec, regionName);
			} else {
				point = new PointRegion(vec);
			}
		}
		return point;
	}

	public static Region parseEmpty(Node regionNode) {
		EmptyRegion empty = null;
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element emptyElement = (Element) regionNode;
			if (emptyElement.hasAttribute("name")) {
				String name = emptyElement.getAttribute("name");
				empty = new EmptyRegion(name);
			} else {
				empty = new EmptyRegion("");
			}
		}
		return empty;
	}

	public static Region parseBlock(Node regionNode) {
		BlockRegion block = null;
		String[] parts = regionNode.getTextContent().split(",");
		double x = NumberUtils.parseDouble(parts[0]);
		double y = NumberUtils.parseDouble(parts[1]);
		double z = NumberUtils.parseDouble(parts[2]);
		InfoModule module = (InfoModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(InfoModule.class);
		String name = "match-" + module.getInfo().getName();
		Location loc = new Location(Bukkit.getWorld(name), x, y, z);
		Vector vec = loc.toVector();
		if (regionNode.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) regionNode;
			if (e.hasAttribute("name")) {
				String attributeName = e.getAttribute("name");
				block = new BlockRegion(vec, attributeName);
			} else {
				block = new BlockRegion(vec);
			}
		}
		return block;
	}

	public static Region parseCuboid(Node node) {
		CuboidRegion cuboid = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element elementNode = (Element) node;
			Vector min = LocationUtils.vectorFromString(elementNode
					.getAttribute("min"));
			Vector max = LocationUtils.vectorFromString(elementNode
					.getAttribute("max"));

			if (elementNode.hasAttribute("name")) {
				String name = elementNode.getAttribute("name");
				cuboid = new CuboidRegion(name, new BlockRegion(min),
						new BlockRegion(max));
			} else {
				cuboid = new CuboidRegion(new BlockRegion(min),
						new BlockRegion(max));
			}
		}
		return cuboid;
	}

	public static Region parseSphere(Node node) {
		SphereRegion sphere = null;
		BlockRegion origin = null;
		int radius;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element nodeElement = (Element) node;
			origin = new BlockRegion(LocationUtils.vectorFromString(nodeElement
					.getAttribute("origin")));
			radius = NumberUtils.parseInteger(nodeElement
					.getAttribute("radius"));
			if (nodeElement.hasAttribute("name")) {
				String name = nodeElement.getAttribute("name");
				sphere = new SphereRegion(name, origin, radius);
			} else {
				sphere = new SphereRegion(origin, radius);
			}
		}
		return sphere;
	}

	public static Region parseCyclinder(Node node) {
		CylinderRegion cylinder = null;
		BlockRegion base = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			base = new BlockRegion(LocationUtils.vectorFromString(element
					.getAttribute("base")));
			int radius = NumberUtils.parseInteger(element
					.getAttribute("radius"));
			int height = NumberUtils.parseInteger(element
					.getAttribute("height"));
			if (element.hasAttribute("name")) {
				String name = element.getAttribute("name");
				cylinder = new CylinderRegion(name, base, radius, height);
			} else {
				cylinder = new CylinderRegion(base, radius, height);
			}
		}
		return cylinder;
	}

	public static boolean isRegionTag(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			switch (node.getNodeName()) {
			case "block":
			case "empty":
			case "point":
			case "cuboid":
			case "sphere":
			case "cylinder":
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