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

@RunWith(Parameterized.class)
public class LionOneParameterNegativeTest {
    String sex;
    @Mock
    private Feline feline;

    public LionOneParameterNegativeTest(String sex) {
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
                "Самка",
                "Бонифаций"
        };
    }

    @Test
    public void LionSexTest() throws Exception {
        try {
            Lion lion = new Lion(feline, sex);
        } catch (Exception e) {
            MatcherAssert.assertThat(sex, allOf(not(equalTo("Самец")), not(equalTo("Самка"))));
            Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }
}