package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

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
        Waffle fit = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fit.isLowSugar()).isTrue();
    }


}