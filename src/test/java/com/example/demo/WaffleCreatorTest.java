package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

class WaffleCreatorTest {

    WaffleCreator waffleCreator;

    @BeforeEach
    void setup() {
        MacronutrientsProvider fit = Mockito.mock(MacronutrientsProvider.class);
        MacronutrientsProvider fat = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(fit.fetch()).thenReturn(new Macronutrients(10, 40, 10));
        Mockito.when(fat.fetch()).thenReturn(new Macronutrients(100, 40, 10));
        waffleCreator = new WaffleCreator(fit, fat);
    }

    @Test
    void canCreateSweetWaffle() {
        //when
        Waffle withSugar = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.SUPER_SWEET);

        //then
        assertThat(withSugar.isLowSugar()).isFalse();
    }

    @Test
    void canCreateFitWaffle() {
        //when
        //MacronutrientsProvider mock = Mockito.mock(MacronutrientsProvider.class);
        //Mockito.when(mock.fetch()).thenReturn(new Macronutrients(10, 40, 10));
        Waffle fit = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fit.isLowSugar()).isTrue();
    }


}