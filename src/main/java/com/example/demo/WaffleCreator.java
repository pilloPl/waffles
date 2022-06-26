package com.example.demo;

import java.util.*;

class WaffleCreator {

    private final MacronutrientsProvider lowSugar;
    private final MacronutrientsProvider highSugar;

    WaffleCreator(
            MacronutrientsProvider lowSugar,
            MacronutrientsProvider highSugar) {
        this.lowSugar = lowSugar;
        this.highSugar = highSugar;
    }

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            Macronutrients macro = lowSugar.fetch();
            if (macro.isLowSugar()) {
                return new Waffle(name, macro);
            }
            throw new HighSugarWaffle();
        }
        if (type == Waffle.Type.HIGH_SUGAR) {
            Macronutrients macro = highSugar.fetch();
            if (macro.isHighSugar()) {
                return new Waffle(name, macro);
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}

interface MacronutrientsProvider {

    Macronutrients fetch();
}

class HighSugarProvider implements MacronutrientsProvider {

    private final String url = "http://gimmeSugar.com";

    public Macronutrients fetch() {
        //do http call...
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

}

class LowSugarProvider implements MacronutrientsProvider {

    private final String url = "http://nosugar.com";

    public Macronutrients fetch() {
        //do http call...
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

}

class HighSugarWaffle extends RuntimeException {

}

class LowSugarWaffle extends RuntimeException {

}