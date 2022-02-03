package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WaffleCreatorTest {

    @Autowired
    WaffleCreator waffleCreator;

    @MockBean(name = "fatProvider")
    MacronutrientsProvider fatProvider;

    @MockBean(name = "lowSugarProvider")
    MacronutrientsProvider lowSugarProvider;

    @BeforeEach
    void setup() {
        Mockito.when(lowSugarProvider.fetch()).thenReturn(new Macronutrients(5, 50, 10));
        Mockito.when(fatProvider.fetch()).thenReturn(new Macronutrients(70, 10, 50));

    }

    @Test
    void canCreateFitWaffle() {
        //when
        Waffle fitDiamond = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fitDiamond.isLowSugar()).isTrue();
    }

    @Test
    void canCreateFatWaffle() {
        //when
        Waffle fatty = waffleCreator.prepare("FATTY", Waffle.Type.SUPER_SWEET);

        //then
        assertThat(fatty.isLowSugar()).isFalse();
    }

}