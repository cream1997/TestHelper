package com.cream.helper.utils;

import com.cream.helper.constant.AppConst;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ClassUtil {

    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> findClasses(String pkgName, Class<T> parentClass) {
        return ClassUtil.findClasses(AppConst.MOCK_MSG_PKG, (clazz) -> {
            if (parentClass.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                return (Class<? extends T>) clazz;
            } else {
                return null;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> findClasses(String pkgName, Function<Class<?>, Class<? extends T>> filter) {
        String path = pkgName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<Class<? extends T>> result = new ArrayList<>();
        File directory = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
        if (!directory.exists()) {
            return result;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return result;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(findClasses(pkgName + "." + file.getName(), filter));
            } else if (file.getName().endsWith(".class")) {
                String className = pkgName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> aClass = Class.forName(className);
                    if (filter == null) {
                        result.add((Class<? extends T>) aClass);
                    } else {
                        Class<? extends T> apply = filter.apply(aClass);
                        if (apply != null) {
                            result.add(apply);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}
