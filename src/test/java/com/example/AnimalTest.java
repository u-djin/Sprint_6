package com.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

@RunWith(Parameterized.class)
public class AnimalTest {

    private String animalKind;
    private List<String> food;

    public AnimalTest(String animalKind, List<String> food) {
        this.animalKind = animalKind;
        this.food = food;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getFoodData() {
        return new Object[][] {
                {"Травоядное", List.of("Трава", "Различные растения")},
                {"Хищник", List.of("Животные", "Птицы", "Рыба")},
                {"нечто", List.of("любое значение для конструктора")}
        };
    }

    @Test
    public void getFoodTest() throws Exception {
        Animal animal = new Animal();
        try {
            List<String> actualFood = animal.getFood(animalKind);
            assertEquals(food, actualFood);
        } catch (Exception e) {
            MatcherAssert.assertThat(animalKind, allOf(not(equalTo("Травоядное")), not(equalTo("Хищник"))));
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}









