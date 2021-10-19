package co.com.sofka.soap.utils.xml;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Xml {

    private static final Logger LOGGER = Logger.getLogger(Xml.class);

    private Document doc;

    private final String path;

    public Xml(String path) {
        this.path = path;
    }

    public static Xml updateXmlFileInThe(String path){
        return new Xml(path);
    }

    public boolean usingNextNodes(Map<String, String> nodeList){
        if(!createDocument(this.path))
            return false;

        Set<String> mapKeys = nodeList.keySet();
        for (String key: mapKeys) {
            if (!updateValueInNodo(key, nodeList.get(key)))
                return false;
        }

        return saveChanges(this.path);

    }

    private boolean createDocument(String documentPath) {
        DocumentBuilderFactory f;
        DocumentBuilder b;
        try {
            f = DocumentBuilderFactory.newInstance();
            b = f.newDocumentBuilder();
            doc = b.parse(new File(documentPath));
        }catch (ParserConfigurationException |IOException |SAXException e){
            LOGGER.info(e.getMessage(), e);
            return false;
        }

        return true;
    }

    private boolean updateValueInNodo(String xpath, String value){
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node;

        try {
            node = (Node) xPath.compile(xpath).evaluate(doc, XPathConstants.NODE);
            if(node == null)
                return false;
            node.setTextContent(value);
        } catch (XPathExpressionException e) {
            LOGGER.info(e.getMessage(), e);
            return false;
        }

        return true;
    }

    private boolean saveChanges(String path){
        try {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult sr = new StreamResult(new File(path));
            tf.transform(domSource, sr);
        } catch (DOMException | TransformerFactoryConfigurationError | IllegalArgumentException | TransformerException e) {
            LOGGER.info(e.getMessage(), e);
            return false;
        }

        return true;
    }

}
