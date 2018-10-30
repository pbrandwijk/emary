package com.pbrandwijk.emary;

import static java.nio.charset.Charset.forName;

import org.apache.commons.io.FileUtils;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Canonize an XML file.
 *
 * @author Pieter Brandwijk
 */
public class XMLCanonicalizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLCanonicalizer.class);
    private static final String DEFAULT_CANONICAL_XML_FILE_PATH = "canonical.xml";
    private static final String DEFAULT_FILE_ENCODING = "UTF-8";

    /**
     * Canonize the given XML file and write out the result.
     *
     * @param xmlFile The file to be canonized
     * @throws InvalidCanonicalizerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws CanonicalizationException
     * @throws IOException
     */
    public static void canonicalize(File xmlFile) throws InvalidCanonicalizerException, ParserConfigurationException,
            SAXException, CanonicalizationException, IOException {
        LOGGER.info("Start canonicalization of XML");
        Init.init();
        Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
        byte canonXmlBytes[] = canon.canonicalize(Files.readAllBytes(xmlFile.toPath()));
        String canonXml = new String(canonXmlBytes);

        FileUtils.writeStringToFile(new File(DEFAULT_CANONICAL_XML_FILE_PATH), canonXml, forName(DEFAULT_FILE_ENCODING));
        LOGGER.info("Canonicalization of XML finished");
    }
}