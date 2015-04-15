package me.skylertyler.scrimmage.modules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
	
	String name() default "";

	Class<? extends Module> module();

	String desc() default "";

	Class<? extends Module>[] requires() default {};  
}
