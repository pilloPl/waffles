package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

    @Test
    void canCreateFitWaffle() {
        //given
        MacronutrientsProvider lowSugar = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(lowSugar.fetch()).thenReturn(new Macronutrients(5, 50, 10));
        MacronutrientsProviderFactory.put("low sugar", lowSugar);

        //when
        Waffle fitDiamond = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fitDiamond.isLowSugar()).isTrue();
    }

    @Test
    void canCreateFatWaffle() {
        //given
        MacronutrientsProvider muchSugar = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(muchSugar.fetch()).thenReturn(new Macronutrients(100, 50, 30));
        MacronutrientsProviderFactory.put("fat", muchSugar);

        //when
        Waffle fitDiamond = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.SUPER_SWEET);

        //then
        assertThat(fitDiamond.isLowSugar()).isFalse();
    }

}