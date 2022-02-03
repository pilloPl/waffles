package com.example.demo;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

import java.util.*;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = (MacronutrientsProvider) TypeFactory.get(MacronutrientsProvider.class, "low sugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooFatWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider
                    = (MacronutrientsProvider) TypeFactory.get(MacronutrientsProvider.class, "fat");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}

class Dep {

    String name;
    Object dep;

    Dep(String name, Object dep) {
        this.name = name;
        this.dep = dep;
    }
}

class TypeFactory {


    static Map<Class, List<Dep>> providers = new HashMap<>();

    static void put(String name, Object object) {
        put(name, object, object.getClass());
    }

    public static void put(String name, Object object, Class cls) {
        List<Dep> deps = providers.getOrDefault(cls, new ArrayList<>());
        deps.add(new Dep(name, object));
        providers.put(cls, deps);
    }

    static Object get(Class cls) {
        List<Dep> deps = providers.get(cls);
        if (deps.size() == 1) {
            return deps.get(0);
        }
        throw new NoUniqueBeanDefinitionException(cls, "names of found beans");
    }

    static Object get(Class cls, String name) {
        return providers.getOrDefault(cls, new ArrayList<>())
                .stream()
                .filter(dep -> dep.name.equals(name))
                .map(dep -> dep.dep)
                .findFirst()
                .orElseThrow(() -> new NoSuchBeanDefinitionException(name));

    }


}


class MacronutrientsProvider {

    private final String url;

    MacronutrientsProvider(String url) {
        this.url = url;
    }

    Macronutrients fetch() {
        //do http call...
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }


}

class Macronutrients {
    private final float sugar;
    private final float protein;
    private final float fat;


    Macronutrients(float sugar, float protein, float fat) {
        this.sugar = sugar;
        this.protein = protein;
        this.fat = fat;
    }

    public boolean hasMuchSugar() {
        return sugar > 50;
    }

    public boolean isFit() {
        return protein > 30 && sugar < 15;
    }
}

class TooFatWaffle extends RuntimeException {

}

class NotEnoughSugar extends RuntimeException {

}