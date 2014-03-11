package com.depeng.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TryWebUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHello() throws Exception {
        new WebUtils().hello(null);
    }
}
