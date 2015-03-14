package me.skylertyler.scrimmage.modules;

public class ModuleInfo {

	protected final Class<? extends Module> module;
	protected final ModuleDescription description;

	public ModuleInfo(Class<? extends Module> module) {
		this.module = module;
		this.description = module.getAnnotation(ModuleDescription.class);

		if (this.description == null) {
			throw new IllegalStateException(
					"Module needs to have a ModuleDescription @annotation! ");
		}
	}

	public String getName() {
		return this.description.name();
	}

	public Class<? extends Module>[] getDepends() {
		return this.description.depends();
	}

	public ModuleDescription getDescription() {
		return this.description;
	}

	public Class<? extends Module> getModuleClass() {
		return this.module;
	}
}
