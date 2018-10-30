package com.pbrandwijk.emary;

import com.pbrandwijk.emary.util.LogErrorListener;
import com.pbrandwijk.emary.util.VoidErrorHandler;
import net.sf.saxon.TransformerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is responsible for applying al XSLT style sheet to an XML.
 *
 * @author Pieter Brandwijk
 */
public class Transformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformer.class);
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

    /**
     * Transform the given XML file with the given XSLT and output the result to the given file.
     *
     * @param xmlStream The XML to be transformed
     * @param xsltStream The XSLT to be used for the transformation
     * @param outputFile The file to write the output of the transformation to
     * @return The output stream of XML after the transformation
     */
    public static OutputStream transform(InputStream xmlStream, InputStream xsltStream, File outputFile)
        throws ParserConfigurationException, SAXException, IOException, TransformerException {

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

        // Return the output XML file
        return result.getOutputStream();
    }


}
