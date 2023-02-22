package com.facenet.mina.utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class XmlUtils {

    /**
     * Using when want to convert String XML to Document
     *
     * @param xml - String of XML Element
     * @return
     * @throws Exception
     */
    public static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
}
