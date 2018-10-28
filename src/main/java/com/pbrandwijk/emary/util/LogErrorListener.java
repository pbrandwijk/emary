package com.pbrandwijk.emary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/**
 * Created by PBRANDWI on 16-4-14.
 */
public class LogErrorListener implements ErrorListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogErrorListener.class);

    @Override
    public void warning(TransformerException exception) throws TransformerException {
        LOGGER.warn(exception.getMessage());
    }

    @Override
    public void error(TransformerException exception) throws TransformerException {
        LOGGER.error(exception.getMessage());
    }

    @Override
    public void fatalError(TransformerException exception) throws TransformerException {
        LOGGER.error(exception.getMessage());
    }
}
