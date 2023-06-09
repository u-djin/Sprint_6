package com.example;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineAnimalKindTest {
    private String animalKind;

    public FelineAnimalKindTest(String animalKind) {
        this.animalKind = animalKind;
    }

    @Parameterized.Parameters
    public static Object[] FelineData() {
        return new Object[] {
                "Хищник"
        };
    }

    @Mock
    Feline feline;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void eatMeatTest() throws Exception {
        try {
            Mockito.when(feline.getFood(animalKind)).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            feline.eatMeat();
            Mockito.verify(feline, Mockito.times(1)).eatMeat();
        } catch (Exception e) {
            MatcherAssert.assertThat(animalKind, allOf(not(equalTo("Травоядное")), not(equalTo("Хищник"))));
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}