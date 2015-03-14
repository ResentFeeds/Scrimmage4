package me.skylertyler.scrimmage.modules;

import org.w3c.dom.Document;

@ModuleDescription(name = "anothertest", depends = { TestModule.class }, description = "test")
public class AnotherTestModule extends Module {

	@Override
	public void unload() {
	}

	@Override
	public void load(Document doc) {
	} 
}
