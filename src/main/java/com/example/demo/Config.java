package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Config {

    WaffleCreator waffleCreator() {
        return new WaffleCreator(lowSugarProvider(), highSugarProvider());
    }

    MacronutrientsProvider highSugarProvider() {
        return new HighSugarProvider();
    }

    MacronutrientsProvider lowSugarProvider() {
        return new LowSugarProvider();
    }
}
