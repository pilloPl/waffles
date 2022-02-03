package com.example.demo;

import java.util.Random;

class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://lowsugar.com");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooFatWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider
                    = new MacronutrientsProvider("http://ilovesugar.com");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            }
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