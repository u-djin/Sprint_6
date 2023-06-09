package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
        assertEquals("Кошачьи", felineSpy.getFamily());
        Mockito.verify(felineSpy, Mockito.times(1)).getFamily();
    }
}