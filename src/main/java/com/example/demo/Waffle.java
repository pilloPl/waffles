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
