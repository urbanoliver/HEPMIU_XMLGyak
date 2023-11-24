package hu.domparse.hepmiu;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteHepmiu {
	public static void main(String[] args) {
		try {

			File xmlFile = new File("XMLHepmiu.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Element gyokerElem = doc.getDocumentElement();
			System.out.println("Gyökér elem: " + gyokerElem.getNodeName());
			kiirTartalom(gyokerElem, "");

			writeToFile(doc, "XMLHepmiu1.xml"); // UJ XML FAJLT LETREHOZZA

			System.out.println("XML updatetelt változata mentve az XMLHepmiu1.xml fájlba");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void kiirTartalom(Node node, String indent) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(indent + node.getNodeName());

			if (node.hasAttributes()) {
				NamedNodeMap attrib = node.getAttributes();
				for (int i = 0; i < attrib.getLength(); i++) {
					Node attribute = attrib.item(i);
					System.out.println(indent + attribute.getNodeName() + " = " + attribute.getNodeValue());
				}
			}

			if (node.hasChildNodes()) {
				NodeList gyerek = node.getChildNodes();
				for (int i = 0; i < gyerek.getLength(); i++) {
					Node child = gyerek.item(i);
					kiirTartalom(child, indent + "  ");
				}
			}
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			String datas = node.getNodeValue().trim();
			if (!datas.isEmpty()) {
				System.out.println(indent + datas);
			}
		}
	}

	public static void writeToFile(Document doc, String filename) { // A MODOSITOTT FAJLT LETREHOZO METODUS
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
