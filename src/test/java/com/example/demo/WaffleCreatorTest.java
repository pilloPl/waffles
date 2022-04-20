package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WaffleCreatorTest {

    @Autowired
    WaffleCreator waffleCreator;

    @MockBean
    @Autowired
    @Qualifier("fit")
    MacronutrientsProvider fit;

    @MockBean
    @Autowired
    @Qualifier("fat")
    MacronutrientsProvider fat;

    @Test
    void canCreateSweetWaffle() {
        //when
        Mockito.when(fat.fetch()).thenReturn(new Macronutrients(100, 10,10));
        Waffle withSugar = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.SUPER_SWEET);

        //then
        assertThat(withSugar.isLowSugar()).isFalse();
    }

    @Test
    void canCreateFitWaffle() {
        //when
        Mockito.when(fit.fetch()).thenReturn(new Macronutrients(10, 50,10));
        Waffle fit = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fit.isLowSugar()).isTrue();
    }


}