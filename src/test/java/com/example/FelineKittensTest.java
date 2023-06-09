package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class FelineKittensTest {
    private int kittensCount;

    public FelineKittensTest(int kittensCount) {
        this.kittensCount = kittensCount;
    }

    @Parameterized.Parameters
    public static Object[] kittensData() {
        return new Object[] {
                7,
                0,
                -1    //здесь тест на getKittens упадёт
        };
    }

    Feline feline = new Feline();
    Feline felineSpy = Mockito.spy(feline);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getKittensWithParam() {
        Assert.assertEquals(felineSpy.getKittens(kittensCount), kittensCount);
        Assert.assertTrue(felineSpy.getKittens(kittensCount) >= 0);
    }
}