package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

    @Test
    void canCreateLowSugarWaffle() {
        //given
        MacronutrientsProvider stubbed = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(stubbed.fetch()).thenReturn(new Macronutrients(1, 1, 1));

        //when
        Waffle lowSugarBar = waffleCreator.prepare("PROTEIN BAR", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(lowSugarBar.isLowSugar()).isTrue();

    }

    @Test
    void canCreateHighSugarWaffle() {
        //given
        MacronutrientsProvider stubbed = Mockito.mock(MacronutrientsProvider.class);
        Mockito.when(stubbed.fetch()).thenReturn(new Macronutrients(100, 1, 1));
        //when
        Waffle highSugarBar = waffleCreator.prepare("CHOCOLATE BAR", Waffle.Type.HIGH_SUGAR);

        //then
        assertThat(highSugarBar.isLowSugar()).isFalse();
    }

}