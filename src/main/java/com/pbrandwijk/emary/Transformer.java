package com.pbrandwijk.emary;

import com.pbrandwijk.emary.util.LogErrorListener;
import com.pbrandwijk.emary.util.VoidErrorHandler;
import net.sf.saxon.TransformerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;

/**
 * This class is responsible for applying al XSLT style sheet to an XML.
 *
 * @author Pieter Brandwijk
 */
public class Transformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformer.class);
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
    private static final String DEFAULT_OUTPUT_FILE_PATH = "output.xml";
    private static final String DEFAULT_XSLT_FILE_PATH = "xslt/stylesheet.xsl";

    /**
     * Transform the given XML file with the default XSLT and output the result to the default output file.
     *
     * @param xmlStream
     */
    public static void transform(InputStream xmlStream) {
        InputStream xsltStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_XSLT_FILE_PATH);
        transform(xmlStream, xsltStream);
    }

    /**
     * Transform the given XML file with the default XSLT and output the result to the given file.
     *
     * @param xmlStream
     * @param xsltStream
     */
    public static void transform(InputStream xmlStream, InputStream xsltStream) {
        transform(xmlStream, xsltStream, new File(DEFAULT_OUTPUT_FILE_PATH));
    }

    /**
     * Transform the given XML file with the given XSLT and output the result to the given file.
     *
     * @param xmlStream
     * @param xsltStream
     * @param outputFile
     */
    public static void transform(InputStream xmlStream, InputStream xsltStream, File outputFile) {

        try {
            // Parse the XML stream to an XML Document
            DocumentBuilder voidBuilder = FACTORY.newDocumentBuilder();
            voidBuilder.setErrorHandler(new VoidErrorHandler());
            Document xmlDocument = voidBuilder.parse(xmlStream);

            // Do the transformation, track the running time
            TransformerFactory fact = new TransformerFactoryImpl();
            StreamSource styleSource = new StreamSource(xsltStream);
            javax.xml.transform.Transformer trans = fact.newTransformer(styleSource);
            trans.setErrorListener(new LogErrorListener());
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(outputFile);
            double beginTrans = System.currentTimeMillis();
            trans.transform(source, result);
            double endTrans = System.currentTimeMillis();
            LOGGER.info("Running time of the transformation: " + Double.toString((endTrans - beginTrans) / 1000) + " sec");
        } catch (Exception e) {
            LOGGER.warn("An exception occurred during the transformation", e);
        }

    }


}
