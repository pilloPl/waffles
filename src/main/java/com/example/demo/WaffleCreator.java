package com.example.demo;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class WaffleCreator {

    private final MacronutrientsProvider lowSugar;
    private final MacronutrientsProvider fat;

    WaffleCreator(@Qualifier("lowSugarProvider")
                          MacronutrientsProvider lowSugar,
                  @Qualifier("fatProvider")
                          MacronutrientsProvider fat) {
        this.lowSugar = lowSugar;
        this.fat = fat;
    }

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            Macronutrients macro = lowSugar.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooFatWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            Macronutrients macro = fat.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}


interface MacronutrientsProvider {

    Macronutrients fetch();
}

@Component
class FatProvider implements MacronutrientsProvider {

    private final String url = "http://fat.com";

    public Macronutrients fetch() {
        //do http call...
        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

}

@Component
class LowSugarProvider implements MacronutrientsProvider {

    private final String url = "http://lowsugar.com";

    public Macronutrients fetch() {
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