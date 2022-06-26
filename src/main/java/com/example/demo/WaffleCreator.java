package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class WaffleCreator {

    private final MacronutrientsProvider lowSugar;
    private final MacronutrientsProvider highSugar;

    WaffleCreator(
           @Qualifier("lowSugarProvider") MacronutrientsProvider ls,
            MacronutrientsProvider highSugarProvider) {
        this.lowSugar = ls;
        this.highSugar = highSugarProvider;
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

@Service("highSugarProvider")
class HighSugarProvider implements MacronutrientsProvider {

    private final String url = "http://gimmeSugar.com";

    public Macronutrients fetch() {
        //do http call...
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

}

@Component("lowSugarProvider")
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