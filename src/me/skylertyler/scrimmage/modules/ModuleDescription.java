package me.skylertyler.scrimmage.modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleDescription {
	/** Name of this module. */
	public String name();

	/** List of modules that need to be loaded before this module. */
	public Class<? extends Module>[] depends() default {};

	/** description about why this module is loaded for! */
	public String description();

}
