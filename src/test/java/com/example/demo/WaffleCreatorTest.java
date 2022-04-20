package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

    @Test
    void canCreateSweetWaffle() {
        //when
        MacronutrientsProvider mock = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(mock.fetch()).thenReturn(new Macronutrients(100, 10, 10));
        ServiceLocator.put("much sugar", mock, MacronutrientsProvider.class);
        Waffle withSugar = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.SUPER_SWEET);

        //then
        assertThat(withSugar.isLowSugar()).isFalse();
    }

    @Test
    void canCreateFitWaffle() {
        //when
        MacronutrientsProvider mock = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(mock.fetch()).thenReturn(new Macronutrients(10, 40, 10));
        ServiceLocator.put("fit", mock, MacronutrientsProvider.class);
        Waffle fit = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fit.isLowSugar()).isTrue();
    }


}