package com.example.demo;

import java.util.*;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    ServiceLocator.get(MacronutrientsProvider.class, "nosugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isLowSugar()) {
                return new Waffle(name, macro);
            }
            throw new HighSugarWaffle();
        }
        if (type == Waffle.Type.HIGH_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    ServiceLocator.get(MacronutrientsProvider.class, "gimmeSugar");
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


class DepDefinition {

    String name;
    Object dep;

    DepDefinition(String name, Object dep) {
        this.name = name;
        this.dep = dep;
    }
}

class ServiceLocator {

    static Map<Class, List<DepDefinition>> providers = new HashMap<>();

    static void put(String name, Object object) {
        put(name, object, object.getClass());
    }

    public static void put(String name, Object object, Class cls) {

    }

    static <T> T get(Class<T> cls) {
       return null;
    }

    static <T> T get(Class<T> cls, String name) {
        return null;
    }


}

class NoUniqueDepDefinitionException extends RuntimeException {

}

class NoSuchDepDefinitionException extends RuntimeException {

}