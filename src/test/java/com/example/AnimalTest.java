package com.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class AnimalTest {

    private String animalType;
    private List<String> food;

    public AnimalTest(String animalType, List<String> food) {
        this.animalType = animalType;
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
                {"нечто", Collections.emptyList()},
        };
    }

    @Test
    public void getFoodTest() throws Exception {
        Animal animal = new Animal();
        try {
            List<String> actualFood = animal.getFood(animalType);
            assertEquals(food, actualFood);
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}









