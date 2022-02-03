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
