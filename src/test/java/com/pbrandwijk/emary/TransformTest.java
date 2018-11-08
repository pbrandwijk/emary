package com.pbrandwijk.emary;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Test the transformation of an XML document.
 *
 * @author Pieter Brandwijk
 */
public class TransformTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformTest.class);
    private static final String DEFAULT_XSLT_FILE_PATH = "build/resources/test/stylesheet.xsl";
    private static final String DEFAULT_OUTPUT_FILE_PATH = "build/resources/test/output.xml";

    /**
     * Test that runs the transform method on a sample XML file to check that the code executes correctly.
     *
     * @throws Exception
     */
    @Test
    public void transformTest() throws Exception{
        File xmlFile = new File("build/resources/test/transformTest.xml");
        File xsltFile = new File(DEFAULT_XSLT_FILE_PATH);
        File outputFile = new File(DEFAULT_OUTPUT_FILE_PATH);
        InputStream xmlStream = new FileInputStream(xmlFile);
        InputStream xsltStream = new FileInputStream(xsltFile);
        Transformer.transform(xmlStream, xsltStream, outputFile);
    }

}
