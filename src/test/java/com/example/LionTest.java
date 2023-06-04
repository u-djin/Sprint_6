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
public class LionTest {
    boolean hasMane;
    String sex;
    @Mock
    private Feline feline;

    public LionTest(String sex, boolean hasMane) {
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][] {
                {"Самец", true},
                {"Самка", false},
                {"Бонифаций", true}     //значение true поставлено на случай, если вдруг в тесте не будет выброшено исключение.
                                        //тогда в экземпляре Lion поле hasMane не будет инициализировано и по умолчанию будет иметь значение false
                                        //в этом случае Assert должен провалить проверку
        };
    }

    @Test
    public void LionSexTest() throws Exception {
        try {
            Lion lion = new Lion(feline, sex);
            Assert.assertEquals(lion.hasMane, hasMane);
        } catch (Exception e) {
            MatcherAssert.assertThat(sex, allOf(not(equalTo("Самец")), not(equalTo("Самка"))));
            Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lionFemale = new Lion(feline, "Самка");
        lionFemale.getKittens();
        Lion lionMale = new Lion(feline, "Самец");
        lionMale.getKittens();
        Mockito.verify(feline, Mockito.times(2)).getKittens();
    }

    @Test
    public void doesHaveManeTest() throws Exception{
        Lion lionFemale = new Lion(feline, "Самка");
        assertFalse(lionFemale.doesHaveMane());
        Lion lionMale = new Lion(feline, "Самец");
        assertTrue(lionMale.doesHaveMane());
    }

    @Test
    public void getFoodTest() throws  Exception{
        Lion lionFemale = new Lion(feline, "Самка");
        Lion lionMale = new Lion(feline, "Самец");
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        lionFemale.getFood();
        lionMale.getFood();
        Mockito.verify(feline, Mockito.times(2)).getFood("Хищник");
    }
}