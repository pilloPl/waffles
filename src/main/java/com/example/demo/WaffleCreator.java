package com.example.demo;

import java.util.HashMap;
import java.util.Map;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://lowsugar.com");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooSweetWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://ilovesugar.com");
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

class MacronutrientsProviderLocator {

    static Map<String, MacronutrientsProvider> providers = new HashMap<>();

    static void put(String name, MacronutrientsProvider provider) {
        providers.put(name, provider);
    }

    static MacronutrientsProvider get(String name) {
        return providers.get(name);
    }

}


class TooSweetWaffle extends RuntimeException {

}

class NotEnoughSugar extends RuntimeException {

}