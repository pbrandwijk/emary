package com.pbrandwijk.emary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Main entry point of the program. Responsible for parsing the arguments given to it on the command line and
 * selecting the appropriate execution path.
 *
 * @author Pieter Brandwijk
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String DEFAULT_OUTPUT_FILE_PATH = "output.xml";

    /**
     * Entry method for the program. Select execution path based on number of arguments given. Assumed order:
     * - XML file to be transformed
     * - XSLT file to be used for the transformation
     * - Output path where to write the output to
     *
     * @param args The arguments given to this program on the command line
     */
    public static void main(String[] args) {

        // Determine the program execution based on the number of arguments
        switch (args.length) {
            case 0:
                LOGGER.error("No XML file and no XSLT file is specified");
                break;
            case 1:
                LOGGER.error("No XML or XSLT file is specified");
                break;
            case 2:
                transformXML(args[0], args[1]);
                break;
            case 3:
                transformXML(args[0], args[1], args[2]);
                break;
            default:
                LOGGER.error("Unknown argument: " + args[3]);
        }
    }

    private static void transformXML(String xmlPath, String xsltPath) {
        transformXML(xmlPath, xsltPath, DEFAULT_OUTPUT_FILE_PATH);
    }

    private static void transformXML(String xmlPath, String xsltPath, String outputPath) {
        File xmlFile = new File(xmlPath);
        File xsltFile = new File(xsltPath);
        File outputFile = new File(outputPath);

        try {
            Transformer.transform(new FileInputStream(xmlFile), new FileInputStream(xsltFile), outputFile);
        } catch (FileNotFoundException e) {
            LOGGER.error("File could not be found", e);
        } catch (Exception e) {
            LOGGER.warn("An exception occurred during the transformation", e);
        }
    }
}