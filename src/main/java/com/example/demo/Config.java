package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    WaffleCreator waffleCreator() {
        return new WaffleCreator(lowSugarProvider(), fatProvider());
    }

    @Bean
    MacronutrientsProvider fatProvider() {
        return new FatProvider();
    }

    @Bean
    MacronutrientsProvider lowSugarProvider() {
        return new LowSugarProvider();
    }
}
