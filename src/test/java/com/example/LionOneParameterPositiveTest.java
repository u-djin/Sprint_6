package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

@RunWith(Parameterized.class)
public class LionOneParameterPositiveTest {
    String sex;
    @Mock
    private Feline feline;

    public LionOneParameterPositiveTest(String sex) {
        this.sex = sex;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters
    public static Object[] getParams() {
        return new Object[] {
                "Самец",
                "Самка"
        };
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion(feline, sex);
        lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    public void getFoodTest() throws  Exception{
        Lion lion = new Lion(feline, sex);
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }
}