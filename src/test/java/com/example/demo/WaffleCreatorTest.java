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

    @MockBean(name = "highSugarProvider")
    MacronutrientsProvider highSugarProvider;

    @MockBean(name = "lowSugarProvider")
    MacronutrientsProvider lowSugarProvider;

    @BeforeEach
    void setup() {
        Mockito.when(lowSugarProvider.fetch()).thenReturn(new Macronutrients(5, 50, 10));
        Mockito.when(highSugarProvider.fetch()).thenReturn(new Macronutrients(70, 10, 50));

    }

    @Test
    void canCreateLowSugarWaffle() {
        //when
        Waffle lowSugarBar = waffleCreator.prepare("PROTEIN BAR", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(lowSugarBar.isLowSugar()).isTrue();

    }

    @Test
    void canCreateHighSugarWaffle() {
        //when
        Waffle highSugarBar = waffleCreator.prepare("CHOCOLATE BAR", Waffle.Type.HIGH_SUGAR);

        //then
        assertThat(highSugarBar.isLowSugar()).isFalse();
    }

}