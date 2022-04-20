package com.example.demo;

class Waffle {

    enum Type {
        LOW_SUGAR, SUPER_SWEET, REGULAR
    }

    private final String name;
    private final Macronutrients macro;

    Waffle(String name, Macronutrients macro) {
        this.name = name;
        this.macro = macro;
    }

    boolean isLowSugar() {
        return !macro.hasMuchSugar();
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