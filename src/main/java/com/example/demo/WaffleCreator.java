package com.example.demo;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import java.util.*;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "nosugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isLowSugar()) {
                return new Waffle(name, macro);
            }
            throw new HighSugarWaffle();
        }
        if (type == Waffle.Type.HIGH_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "gimmeSugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isHighSugar()) {
                return new Waffle(name, macro);
            }
            throw new LowSugarWaffle();
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}


class MacronutrientsProvider {
    private final String url;

    MacronutrientsProvider(String url) {
        this.url = url;
    }

    Macronutrients fetch() {
        //do http call...
        if (new Random().nextBoolean()) {
            throw new IllegalStateException();
        }
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat() * 100, rand.nextFloat() * 100, rand.nextFloat() * 100);
    }

}


class HighSugarWaffle extends RuntimeException {

}

class LowSugarWaffle extends RuntimeException {

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
class NoUniqueDepDefinitionException extends RuntimeException {

}

class NoSuchDepDefinitionException extends RuntimeException {

}