package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionTwoParametersTest {
    boolean hasMane;
    String sex;
    @Mock
    private Feline feline;

    public LionTwoParametersTest(String sex, boolean hasMane) {
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][] {
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Test
    public void doesHaveManeTest() throws Exception{
        Lion lion = new Lion(feline, sex);
        assertEquals(hasMane, lion.doesHaveMane());
    }
}