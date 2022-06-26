package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = MacronutrientsProviderLocator.get("nosugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isLowSugar()) {
                return new Waffle(name, macro);
            }
            throw new HighSugarWaffle();
        }
        if (type == Waffle.Type.HIGH_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = MacronutrientsProviderLocator.get("gimmeSugar");
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

class MacronutrientsProviderLocator {

    static Map<String, MacronutrientsProvider> providers = new HashMap<>();

    static void put(String name, MacronutrientsProvider provider) {
        providers.put(name, provider);
    }

    static MacronutrientsProvider get(String name) {
        return providers.get(name);
    }

}

