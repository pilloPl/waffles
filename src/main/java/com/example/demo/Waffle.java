package com.example.demo;

class Waffle {

    enum Type {
        LOW_SUGAR, HIGH_SUGAR, REGULAR
    }

    private final String name;
    private final Macronutrients macro;

    Waffle(String name, Macronutrients macro) {
        this.name = name;
        this.macro = macro;
    }

    boolean isLowSugar() {
        return !macro.isHighSugar();
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