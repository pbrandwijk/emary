package com.pbrandwijk.emary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by PBRANDWI on 16-4-14.
 */
public class LogErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogErrorHandler.class);

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        LOGGER.warn(exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        LOGGER.error(exception.getLineNumber() + "," + exception.getColumnNumber() + ": " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        LOGGER.error(exception.getMessage());
    }
}
