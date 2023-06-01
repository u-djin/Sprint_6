package com.example;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

@RunWith(Parameterized.class)
public class FelineTest {
    private int kittensCount;

    public FelineTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }



    @Parameterized.Parameters
    public static Object[] kittensData() {
        return new Object[] {
                7,
                0,
                -1      //здесь тест на getKittens упадёт. отчёт в Jacoco делался с закомменченной строкой
        };
    }

    Feline feline = new Feline();
    Feline felineSpy = Mockito.spy(feline);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFamilyTest() {
        assertEquals("Кошачьи", felineSpy.getFamily());
        Mockito.verify(felineSpy, Mockito.times(1)).getFamily();
    }

    @Test
    public void getKittensWithParam() {
        Assert.assertEquals(felineSpy.getKittens(kittensCount), kittensCount);
        Assert.assertTrue(felineSpy.getKittens(kittensCount) >= 0);
    }

    @Test
    public void getKittensWithoutParams() {
        felineSpy.getKittens();
        Mockito.verify(felineSpy, Mockito.times(1)).getKittens(1);
    }

    @Test
    public void eatMeatTest() throws Exception {
        try {
            Mockito.when(felineSpy.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            felineSpy.eatMeat();
            Mockito.verify(felineSpy, Mockito.times(1)).eatMeat();
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}