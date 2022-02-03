package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

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
        Waffle fitDiamond = waffleCreator.prepare("FIT DIAMOND", Waffle.Type.LOW_SUGAR);

        //then
        assertThat(fitDiamond.isLowSugar()).isTrue();
    }

}