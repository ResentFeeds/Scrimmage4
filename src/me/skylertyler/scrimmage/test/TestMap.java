package me.skylertyler.scrimmage.test;

import java.io.File;

public class TestMap {

	private final File xml;
	private final TestInfo info;

	public TestMap(File xml, TestInfo info) {
		this.xml = xml;
		this.info = info;
	}

	public File getXML() {
		return this.xml;
	}

	public TestInfo getInfo() {
		return this.info;
	}
}
