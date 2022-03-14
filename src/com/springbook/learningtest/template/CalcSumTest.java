package com.springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CalcSumTest {
    Calculator calculator;
    String numFilePath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(this.numFilePath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilePath), is(24));
    }

    @Test
    public void concatenateStrings() throws Exception {
        assertThat(calculator.concatenate(this.numFilePath), is("1234"));
    }

}
