package me.skylertyler.scrimmage.modules;

import com.google.common.collect.Lists;

import java.util.List;

import me.skylertyler.scrimmage.exception.InvalidModuleException;

public class ModuleRegistry {
    private static List<ModuleInfo> modules = Lists.newArrayList();

    public static void register(Class<? extends Module> clazz) throws InvalidModuleException {
        if(!clazz.isAnnotationPresent(ModuleInfo.class)){
            throw new InvalidModuleException("modules.Module " + clazz + " must have a modules.ModuleInfo annotation present to be valid!");
        }

        modules.add(clazz.getAnnotation(ModuleInfo.class));
    }

    public static List<ModuleInfo> getModules() {
        return modules;
    }
}
