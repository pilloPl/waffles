package com.example.demo;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "fit");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooSweetWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "much sugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            } else {
                throw new NotEnoughSugar();
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}

class BeanDefinition {

    String name;
    Object dep;

    BeanDefinition(String name, Object dep) {
        this.name = name;
        this.dep = dep;
    }
}

class BeanFactory {

    static Map<Class, List<BeanDefinition>> providers = new HashMap<>();

    static void put(String name, Object object) {
        put(name, object, object.getClass());
    }

    public static void put(String name, Object object, Class cls) {
        List<BeanDefinition> deps = providers.getOrDefault(cls, new ArrayList<>());
        deps.add(new BeanDefinition(name, object));
        providers.put(cls, deps);
    }

    static <T> T get(Class<T> cls) {
        List<BeanDefinition> deps = providers.get(cls);
        if (deps.size() == 1) {
            return cls.cast(deps.get(0));
        }
        throw new NoUniqueBeanDefinitionException(cls);
    }

    static <T> T get(Class<T> cls, String name) {
        return providers.getOrDefault(cls, new ArrayList<>())
                .stream()
                .filter(dep -> dep.name.equals(name))
                .map(dep -> cls.cast(dep.dep))
                .findFirst()
                .orElseThrow(() -> new NoSuchBeanDefinitionException(cls));

    }


}


class TooSweetWaffle extends RuntimeException {

}

class NotEnoughSugar extends RuntimeException {

}

class NoUniqueDepDefinitionException extends RuntimeException {

}

class NoSuchDepDefinitionException extends RuntimeException {

}