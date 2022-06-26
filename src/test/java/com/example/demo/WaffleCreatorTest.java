package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WaffleCreatorTest {

    WaffleCreator waffleCreator = new WaffleCreator();

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