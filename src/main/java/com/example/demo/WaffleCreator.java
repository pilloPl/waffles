package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    TypeFactory.get(MacronutrientsProvider.class, "fit");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooSweetWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider =
                    TypeFactory.get(MacronutrientsProvider.class, "much sugar");
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

class DepDefinition {

    String name;
    Object dep;

    DepDefinition(String name, Object dep) {
        this.name = name;
        this.dep = dep;
    }
}

class TypeFactory {

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


class TooSweetWaffle extends RuntimeException {

}

class NotEnoughSugar extends RuntimeException {

}

class NoUniqueDepDefinitionException extends RuntimeException {

}

class NoSuchDepDefinitionException extends RuntimeException {

}