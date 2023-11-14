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
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();	//SZUKSEGES DOM OSZTALYOK PELDANYOSITASA
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			
			printTreeStructure(doc, 0);													//KIIRJA FA STRUKTURABA AZ ADATOKAT

			
			writeToFile(doc, "XMLHepmiu1.xml");											//UJ XML FAJLT LETREHOZZA

			System.out.println("XML structure printed to console and updated content written to output.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void printTreeStructure(Node node, int level) {						//XML ADATOK FA STRUKTURABAN TORTENO KIIRASA
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(getIndent(level) + "Node: " + node.getNodeName());
			NamedNodeMap attributes = node.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				Node attribute = attributes.item(i);
				System.out.println(getIndent(level + 1) + "Attribute: " + attribute.getNodeName() + " = "
						+ attribute.getNodeValue());
			}
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				printTreeStructure(children.item(i), level + 1);
			}
		}
	}

	
	public static String getIndent(int level) {											//FA STRUKTURA LETREHOZASA
		StringBuilder indent = new StringBuilder();
		for (int i = 0; i < level; i++) {
			indent.append("  ");
		}
		return indent.toString();
	}

	
	public static void writeToFile(Document doc, String filename) {						//A MODOSITOTT FAJLT LETREHOZO METODUS
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
