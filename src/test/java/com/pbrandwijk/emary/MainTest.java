package com.pbrandwijk.emary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

    @Test
    public void testTrueIsNotFalse(){
        assertEquals("True is not false", true, true != false);
    }
}