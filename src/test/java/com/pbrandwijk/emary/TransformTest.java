package com.pbrandwijk.emary;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Test the transformation of a STEP document to a PRISMA document.
 *
 * @author Pieter Brandwijk
 */
public class TransformTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformTest.class);

    /**
     * Test that runs the transform method on a sample XML file to check that the code executes correctly.
     *
     * @throws Exception
     */
    @Test
    public void transformTest() throws Exception{
        File xmlFile = new File("build/resources/test/transformTest.xml");
        InputStream xmlStream = new FileInputStream(xmlFile);
        Transformer.transform(xmlStream);
    }

}
