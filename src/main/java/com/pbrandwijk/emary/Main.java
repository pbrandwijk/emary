package com.pbrandwijk.emary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            Transformer.transform(new FileInputStream(new File(args[0])));
        } catch (FileNotFoundException e) {
            LOGGER.warn("XML file could not be found", e);
        }
    }

}