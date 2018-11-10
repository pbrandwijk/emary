package com.pbrandwijk.emary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

/**
 * Main entry point of the program. Responsible for passing the command line arguments to the command class and calling
 * the transformation action on it.
 *
 * @author Pieter Brandwijk
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * Entry method for the program.
     *
     * @param args The arguments given to this program on the command line
     */
    public static void main(String[] args) {

        LOGGER.trace("Entered into main method, going to populate command");
        ExecutionCommand cmd = CommandLine.populateCommand(new ExecutionCommand(), args);
        LOGGER.trace("Command populated, going to execute transformation");
        cmd.transformXML();
        LOGGER.trace("Transformation executed");
    }
}