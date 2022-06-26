package com.example.demo;

import java.util.Random;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://nosugar.com");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isLowSugar()) {
                return new Waffle(name, macro);
            }
            throw new HighSugarWaffle();
        }
        if (type == Waffle.Type.HIGH_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://gimmesugar.com");
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

        Random rand = new Random(100);
        return new Macronutrients(rand.nextFloat() * 100, rand.nextFloat() * 100, rand.nextFloat() * 100);
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

    public boolean isHighSugar() {
        return sugar > 50;
    }

    public boolean isLowSugar() {
        return sugar <= 40;
    }
}

class HighSugarWaffle extends RuntimeException {

}

class LowSugarWaffle extends RuntimeException {

}