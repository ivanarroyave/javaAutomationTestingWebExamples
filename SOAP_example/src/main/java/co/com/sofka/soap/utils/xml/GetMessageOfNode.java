package co.com.sofka.soap.utils.xml;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetMessageOfNode {

	private static final Logger LOGGER = LogManager.getLogger(GetMessageOfNode.class.getName());
	private static final DocumentBuilderFactory documentBuilder = DocumentBuilderFactory.newInstance();

	private GetMessageOfNode() {
	}

	public static String getValueNode(String message, String nodes) {

		String resultResponse = null;
		Document document;

		try {

			DocumentBuilder db = documentBuilder.newDocumentBuilder();
			document = db.parse(new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)));
			XPath xPath = XPathFactory.newInstance().newXPath();
			Node node = (Node) xPath.compile(nodes).evaluate(document, XPathConstants.NODE);

			resultResponse = node.getTextContent();

		} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
			LOGGER.info(e.getMessage(), e);
		}

		return resultResponse;
	}

	public static boolean existNodeInXML(String message, String nodes) {
		Document document;

		try {
			DocumentBuilder db = documentBuilder.newDocumentBuilder();
			document = db.parse(new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)));
			XPath xPath = XPathFactory.newInstance().newXPath();

			if (xPath.compile(nodes).evaluate(document, XPathConstants.NODE) == null)
				return false;

		} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
			LOGGER.info(e.getMessage(), e);
		}

		return true;
	}

}
