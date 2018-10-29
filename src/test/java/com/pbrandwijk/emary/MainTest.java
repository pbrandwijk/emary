package com.pbrandwijk.emary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);
    private static final String argXMLPath = "build/resources/test/transformTest.xml";
    private static final String argXSLTPath = "build/resources/test/stylesheet.xsl";
    private static final String argOutputPath = "build/resources/test/transformed.xml";

    @Test
    public void testXMLTransformation() {
        String[] args = {argXMLPath};
        Main.main(args);
    }

    @Test
    public void testXMLTransformationWithXSLT() {
        String[] args = {argXMLPath, argXSLTPath};
        Main.main(args);
    }

    @Test
    public void testXMLTransformationWithXSLTtoOutput() {
        String[] args = {argXMLPath, argXSLTPath, argOutputPath};
        Main.main(args);
    }

}