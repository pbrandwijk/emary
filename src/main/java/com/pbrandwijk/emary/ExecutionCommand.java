package com.pbrandwijk.emary;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Option;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class responsible for parsing the command line arguments and executing the program based on the given arguments.
 */
public class ExecutionCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final File TRANSFORMED_OUTPUT_FILE = new File("output.xml");

    @Option(names = { "-f", "--file" }, paramLabel = "FILE", description = "The XML file.")
    private File xmlFile;

    @Option(names = { "-s", "--stylesheet" }, paramLabel = "STYLESHEET", description = "The XSLT style sheet.")
    private File xsltFile;

    @Option(names = { "-o", "--output" }, paramLabel = "OUTPUT", description = "The output file path.")
    private String outputPath;

    public void transformXML() {
        try {
            Transformer.transform(new FileInputStream(xmlFile), new FileInputStream(xsltFile), TRANSFORMED_OUTPUT_FILE);
            XMLCanonicalizer.canonicalize(TRANSFORMED_OUTPUT_FILE, outputPath);
            FileUtils.forceDeleteOnExit(TRANSFORMED_OUTPUT_FILE);
        } catch (FileNotFoundException e) {
            String msg = "File could not be found";
            LOGGER.error(msg, e);
            System.err.println(msg);
            System.exit(1);
        } catch (Exception e) {
            String msg = "An exception occurred during the transformation";
            LOGGER.error(msg, e);
            System.err.println(msg);
            System.exit(1);
        }
    }
}
