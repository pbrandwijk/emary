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

    /**
     * Entry method for the program. Select execution path based on number of arguments given. Assumed order:
     * - XML file to be transformed
     * - XSLT file to be used for the transformation
     * - Output path where to write the output to
     *
     * @param args
     */
    public static void main(String[] args) {

        // Determine the program execution based on the number of arguments
        switch (args.length) {
            case 0:
                LOGGER.error("No XML file to be transformed is specified");
                break;
            case 1:
                transformXML(args[0]);
                break;
            case 2:
                transformXMLWithXSLT(args[0], args[1]);
                break;
            case 3:
                transformXMLWithXSLTToFile(args[0], args[1], args[2]);
                break;
            default:
                LOGGER.error("Unknown argument: " + args[3]);
        }
    }

    private static void transformXML(String xmlPath) {
        File xmlFile = new File(xmlPath);

        try {
            Transformer.transform(new FileInputStream(xmlFile));
        } catch (FileNotFoundException e) {
            LOGGER.error("File could not be found", e);
        }
    }

    private static void transformXMLWithXSLT(String xmlPath, String xsltPath) {
        File xmlFile = new File(xmlPath);
        File xsltFile = new File(xsltPath);

        try {
            Transformer.transform(new FileInputStream(xmlFile), new FileInputStream(xsltFile));
        } catch (FileNotFoundException e) {
            LOGGER.error("File could not be found", e);
        }
    }

    private static void transformXMLWithXSLTToFile(String xmlPath, String xsltPath, String outputPath) {
        File xmlFile = new File(xmlPath);
        File xsltFile = new File(xsltPath);
        File outputFile = new File(outputPath);

        try {
            Transformer.transform(new FileInputStream(xmlFile), new FileInputStream(xsltFile), outputFile);
        } catch (FileNotFoundException e) {
            LOGGER.error("File could not be found", e);
        }
    }
}