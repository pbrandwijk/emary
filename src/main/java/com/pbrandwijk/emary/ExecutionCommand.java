package com.pbrandwijk.emary;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class responsible for parsing the command line arguments and executing the program based on the given arguments.
 */
@CommandLine.Command(name = "emary", description = "Transform XML files.", sortOptions = false)
public class ExecutionCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final File TRANSFORMED_TEMP_FILE = new File("transformed.xml");
    private static final String DEFAULT_OUTPUT_FILE = "output.xml";

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message.")
    boolean usageHelpRequested;

    @Option(names = { "-f", "--file" }, paramLabel = "FILE", description = "The XML file to be transformed.")
    private File xmlFile;

    @Option(names = { "-s", "--stylesheet" }, paramLabel = "STYLESHEET", description = "The XSLT style sheet.")
    private File xsltFile;

    @Option(names = { "-o", "--output" }, paramLabel = "OUTPUT",
            description = "The output file path. (default: ${DEFAULT-VALUE})")
    private String outputPath = DEFAULT_OUTPUT_FILE;

    public void transformXML() {
        try {
            Transformer.transform(new FileInputStream(xmlFile), new FileInputStream(xsltFile), TRANSFORMED_TEMP_FILE);
            XMLCanonicalizer.canonicalize(TRANSFORMED_TEMP_FILE, outputPath);
            FileUtils.forceDeleteOnExit(TRANSFORMED_TEMP_FILE);
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
