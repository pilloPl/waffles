package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
class WaffleCreator {


    private final MacronutrientsProvider fit;
    private final MacronutrientsProvider fat;

    WaffleCreator(MacronutrientsProvider fit, MacronutrientsProvider fat) {
        this.fit = fit;
        this.fat = fat;
    }

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {

            Macronutrients macro = fit.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooSweetWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {

            Macronutrients macro = fat.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            } else {
                throw new NotEnoughSugar();
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
    }

}

@Configuration
class BeanConfig {

    @Bean
    public MacronutrientsProvider fit() {
        return new MacronutrientsProvider("http://fit.com");
    }

    @Bean
    public MacronutrientsProvider fat() {
        return new MacronutrientsProvider("http://fat.com");
    }
}



class TooSweetWaffle extends RuntimeException {

}

class NotEnoughSugar extends RuntimeException {

}

class NoUniqueDepDefinitionException extends RuntimeException {

}

class NoSuchDepDefinitionException extends RuntimeException {

}