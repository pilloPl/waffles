package com.example.demo;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class WaffleCreator {

    Waffle prepare(String name, Waffle.Type type) {
        if (type == Waffle.Type.LOW_SUGAR) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "fit");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.isFit()) {
                return new Waffle(name, macro);
            }
            throw new TooSweetWaffle();
        }
        if (type == Waffle.Type.SUPER_SWEET) {
            MacronutrientsProvider ingredientsProvider =
                    BeanFactory.get(MacronutrientsProvider.class, "much sugar");
            Macronutrients macro = ingredientsProvider.fetch();
            if (macro.hasMuchSugar()) {
                return new Waffle(name, macro);
            } else {
                throw new NotEnoughSugar();
            }
        }
        return new Waffle(name, new Macronutrients(0, 0, 0));
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