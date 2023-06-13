package com.example;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FelineNonParameterizedTest {

    Feline feline = new Feline();
    Feline felineSpy = Mockito.spy(feline);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getKittensWithoutParams() {
        felineSpy.getKittens();
        Mockito.verify(felineSpy, Mockito.times(1)).getKittens(1);
    }

    @Test
    public void getFamilyTest() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void eatMeatTest() throws Exception {
        try {
            assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
        } catch (Exception e) {
            MatcherAssert.assertThat("Хищник", allOf(not(equalTo("Травоядное")), not(equalTo("Хищник"))));
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}