package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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

    @Test
    public void getKittensWithParam() {
        Assert.assertEquals(feline.getKittens(kittensCount), kittensCount);
        Assert.assertTrue(feline.getKittens(kittensCount) >= 0);
    }
}